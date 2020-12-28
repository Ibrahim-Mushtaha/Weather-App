package com.ix.ibrahim7.weatherapp.network

import com.ix.ibrahim7.weatherapp.model.weather.Weather
import io.reactivex.rxjava3.core.Single
import retrofit2.http.*

interface Api {



    @GET("weather")
    fun getWeather(
            @QueryMap queryParams: HashMap<String, String>
    ): Single<Weather>



}
