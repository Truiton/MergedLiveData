package com.truiton.mergedlivedata

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.truiton.mergedlivedata.entity.Favourite
import com.truiton.mergedlivedata.model.Venues
import com.truiton.mergedlivedata.room.FavouriteDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivityViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: FourSquareRepo
    val allFavourites: LiveData<List<Venues>>
    private var selectedIndex: Int = -1


    init {
        val favouriteDao = FavouriteDatabase.getDatabase(application).favouriteDao()
        repository = FourSquareRepo(favouriteDao)
        allFavourites = repository.mediatorLiveData
    }

    fun insert(favourite: Favourite) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(favourite)
    }

    fun getPlaces(place: String) = viewModelScope.launch(Dispatchers.IO) {
        repository.getNearbyPlaces(place)
    }

    fun setPlace(index: Int) = viewModelScope.launch(Dispatchers.IO) {
        selectedIndex = index
    }

    fun getSelectedPlace(): Venues {
        return allFavourites.value!![selectedIndex]
    }

}