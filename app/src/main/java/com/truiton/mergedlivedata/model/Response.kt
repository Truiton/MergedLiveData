package com.truiton.mergedlivedata.model

import com.google.gson.annotations.SerializedName

data class Response (

	@SerializedName("venues") val venues : List<Venues>,
	@SerializedName("geocode") val geocode : Geocode
)