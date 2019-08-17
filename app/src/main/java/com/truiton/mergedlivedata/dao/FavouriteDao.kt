package com.truiton.mergedlivedata.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.truiton.mergedlivedata.entity.Favourite

@Dao
interface FavouriteDao {

    @Query("SELECT * from favourite_table")
    fun getAllFavourites(): LiveData<List<Favourite>>

    @Query("SELECT * from favourite_table WHERE foursquareId LIKE :id")
    fun getFavouritesById(id: String): List<Favourite>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(favourite: Favourite)

    @Query("DELETE FROM favourite_table")
    fun deleteAll()
}