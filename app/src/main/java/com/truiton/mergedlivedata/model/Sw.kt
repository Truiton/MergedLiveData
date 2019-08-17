package com.truiton.mergedlivedata.model

import com.google.gson.annotations.SerializedName

data class Sw (

	@SerializedName("lat") val lat : Double,
	@SerializedName("lng") val lng : Double
)