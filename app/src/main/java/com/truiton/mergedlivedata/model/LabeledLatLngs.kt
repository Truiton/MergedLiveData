package com.truiton.mergedlivedata.model

import com.google.gson.annotations.SerializedName

data class LabeledLatLngs (

	@SerializedName("label") val label : String,
	@SerializedName("lat") val lat : Double,
	@SerializedName("lng") val lng : Double
)