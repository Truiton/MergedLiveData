package com.truiton.mergedlivedata.model

import com.google.gson.annotations.SerializedName

data class Geocode(

    @SerializedName("what") val what: String,
    @SerializedName("where") val where: String,
    @SerializedName("feature") val feature: Feature,
    @SerializedName("parents") val parents: List<String>
)