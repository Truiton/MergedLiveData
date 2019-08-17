package com.truiton.mergedlivedata

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.truiton.mergedlivedata.dao.FavouriteDao
import com.truiton.mergedlivedata.entity.Favourite
import com.truiton.mergedlivedata.model.Venues
import com.truiton.mergedlivedata.rest.FSInterface
import com.truiton.mergedlivedata.rest.RetrofitService


class FourSquareRepo(private val favouriteDao: FavouriteDao) {

    val allVenues: MutableLiveData<List<Venues>> = MutableLiveData()
    var foursquareApi = RetrofitService.createService(FSInterface::class.java)
    val mediatorLiveData = MediatorLiveData<List<Venues>>()
    var roomLiveData: LiveData<List<Venues>> = MutableLiveData()

    init {
        allVenues.value = emptyList()

        mediatorLiveData.addSource(allVenues) {
            mediatorLiveData.value = it
        }

        roomLiveData = Transformations.switchMap(favouriteDao.getAllFavourites()) { favs -> getVenues(favs) }
        mediatorLiveData.addSource(roomLiveData) {
            mediatorLiveData.value = it
        }
    }

    private fun getVenues(favourites: List<Favourite>?): LiveData<List<Venues>>? {
        val venues: MutableLiveData<List<Venues>> = MutableLiveData()
        var list = ArrayList<Venues>(allVenues.value!!)
        for (fav in favourites!!) {
            for (venue in list) {
                if (fav.foursquareId == venue.id) {
                    venue.favourite = fav
                }
            }
        }
        venues.value = list
        return venues
    }


    @WorkerThread
    suspend fun insert(favourite: Favourite) {
        favouriteDao.insert(favourite)
    }

    @WorkerThread
    suspend fun getNearbyPlaces(query: String) {
        val foursquareResponse =
            foursquareApi.getPlacesList(
                " client_id",
                "client_secret",
                "Seattle,+WA",
                query,
                "20180401",
                "20"
            )
        if (foursquareResponse.isSuccessful) {
            var list = foursquareResponse.body()!!.response.venues.toMutableList()
            for (venue in list) {
                if (favouriteDao.getFavouritesById(venue.id).isNotEmpty()) {
                    venue.favourite = favouriteDao.getFavouritesById(venue.id)[0]
                }
            }
            allVenues.postValue(list)
        } else {
            allVenues.postValue(emptyList())
        }

    }
}
