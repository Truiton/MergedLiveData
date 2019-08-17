package com.truiton.mergedlivedata.model

import com.google.gson.annotations.SerializedName

data class Feature(

    @SerializedName("cc") val cc: String,
    @SerializedName("name") val name: String,
    @SerializedName("displayName") val displayName: String,
    @SerializedName("matchedName") val matchedName: String,
    @SerializedName("highlightedName") val highlightedName: String,
    @SerializedName("woeType") val woeType: Int,
    @SerializedName("slug") val slug: String,
    @SerializedName("id") val id: String,
    @SerializedName("longId") val longId: Long,
    @SerializedName("geometry") val geometry: Geometry
)