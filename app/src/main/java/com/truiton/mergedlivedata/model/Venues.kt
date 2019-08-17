package com.truiton.mergedlivedata.model

import com.google.gson.annotations.SerializedName
import com.truiton.mergedlivedata.entity.Favourite

data class Venues(

    @SerializedName("id") val id: String,
    @SerializedName("name") val name: String,
    @SerializedName("contact") val contact: Contact,
    @SerializedName("location") val location: Location,
    @SerializedName("categories") val categories: List<Categories>,
    @SerializedName("verified") val verified: Boolean,
    @SerializedName("stats") val stats: Stats,
    @SerializedName("url") val url: String,
    @SerializedName("allowMenuUrlEdit") val allowMenuUrlEdit: Boolean,
    @SerializedName("beenHere") val beenHere: BeenHere,
    @SerializedName("specials") val specials: Specials,
    @SerializedName("storeId") val storeId: String,
    @SerializedName("hereNow") val hereNow: HereNow,
    @SerializedName("referralId") val referralId: String,
    @SerializedName("venueChains") val venueChains: List<VenueChains>,
    @SerializedName("hasPerk") val hasPerk: Boolean,
    var _favourite: Favourite?
) {

    var favourite: Favourite
        get() = _favourite ?: Favourite("-1", false)
        set(value) {
            _favourite = value
        }
}