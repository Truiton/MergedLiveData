package com.truiton.mergedlivedata.model

import com.google.gson.annotations.SerializedName
import com.truiton.mergedlivedata.model.LabeledLatLngs

data class Location (

    @SerializedName("address") val address : String,
    @SerializedName("crossStreet") val crossStreet : String,
    @SerializedName("lat") val lat : Double,
    @SerializedName("lng") val lng : Double,
    @SerializedName("labeledLatLngs") val labeledLatLngs : List<LabeledLatLngs>,
    @SerializedName("postalCode") val postalCode : Int,
    @SerializedName("cc") val cc : String,
    @SerializedName("city") val city : String,
    @SerializedName("state") val state : String,
    @SerializedName("country") val country : String,
    @SerializedName("formattedAddress") val formattedAddress : List<String>
)