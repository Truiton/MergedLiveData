package com.truiton.mergedlivedata.rest

import com.truiton.mergedlivedata.model.FoursquareResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query


interface FSInterface {
    @GET("v2/venues/search")
    suspend fun getPlacesList(
        @Query("client_id") client_id: String,
        @Query("client_secret") client_secret: String,
        @Query("near") near: String,
        @Query("query") query: String,
        @Query("v") v: String,
        @Query("limit") limit: String
    ): Response<FoursquareResponse>
}