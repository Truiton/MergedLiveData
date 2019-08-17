package com.truiton.mergedlivedata.model

import com.google.gson.annotations.SerializedName

data class Contact(

    @SerializedName("phone") val phone: Int,
    @SerializedName("formattedPhone") val formattedPhone: String,
    @SerializedName("twitter") val twitter: String
)