package com.truiton.mergedlivedata.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.truiton.mergedlivedata.dao.FavouriteDao
import com.truiton.mergedlivedata.entity.Favourite

@Database(entities = arrayOf(Favourite::class), version = 1)
public abstract class FavouriteDatabase : RoomDatabase() {

    abstract fun favouriteDao(): FavouriteDao

    companion object {
        @Volatile
        private var INSTANCE: FavouriteDatabase? = null

        fun getDatabase(context: Context): FavouriteDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    FavouriteDatabase::class.java,
                    "Favourite_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}