package com.ix.ibrahim7.weatherapp.model.city


import com.google.gson.annotations.SerializedName

data class Geoname(
    @SerializedName("cl")
    val cl: String?,
    @SerializedName("code")
    val code: String?,
    @SerializedName("parent")
    val parent: Double?
)