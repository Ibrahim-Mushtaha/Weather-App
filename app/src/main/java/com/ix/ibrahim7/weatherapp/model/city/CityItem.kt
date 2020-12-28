package com.ix.ibrahim7.weatherapp.model.city


import com.google.gson.annotations.SerializedName

data class CityItem(
    @SerializedName("coord")
    val coord: Coord?,
    @SerializedName("country")
    val country: String?,
    @SerializedName("geoname")
    val geoname: Geoname?,
    @SerializedName("id")
    val id: Double?,
    @SerializedName("langs")
    val langs: List<Lang>?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("stat")
    val stat: Stat?,
    @SerializedName("stations")
    val stations: List<Any>?,
    @SerializedName("zoom")
    val zoom: Double?
)