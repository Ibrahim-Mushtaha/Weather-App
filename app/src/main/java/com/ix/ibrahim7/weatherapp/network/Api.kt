package com.ix.ibrahim7.weatherapp.network

import com.ix.ibrahim7.weatherapp.model.Weather
import io.reactivex.rxjava3.core.Single
import retrofit2.http.*
import com.ix.ibrahim7.weatherapp.util.Constant.API_KEY

interface Api {



    @GET("weather")
    fun getWeather(
            @Query("q")
            country: String,
            @Query("appid")
            apiKey: String = API_KEY
    ): Single<Weather>



}
