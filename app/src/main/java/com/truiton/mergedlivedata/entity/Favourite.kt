package com.truiton.mergedlivedata.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favourite_table")
class Favourite(@PrimaryKey @ColumnInfo(name = "foursquareId") var foursquareId: String,
    @ColumnInfo(name = "fav") var favourite: Boolean)