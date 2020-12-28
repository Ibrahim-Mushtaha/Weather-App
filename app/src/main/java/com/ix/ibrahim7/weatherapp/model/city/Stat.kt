package com.ix.ibrahim7.weatherapp.model.city


import com.google.gson.annotations.SerializedName

data class Stat(
    @SerializedName("level")
    val level: Double?,
    @SerializedName("population")
    val population: Double?
)