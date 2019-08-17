package com.truiton.mergedlivedata.model

import com.google.gson.annotations.SerializedName

data class FoursquareResponse (

	@SerializedName("meta") val meta : Meta,
	@SerializedName("response") val response : Response
)