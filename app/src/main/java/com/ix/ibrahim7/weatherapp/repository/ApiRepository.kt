package com.ix.ibrahim7.weatherapp.repository

import com.ix.ibrahim7.weatherapp.network.RetrofitInstance


class ApiRepository {

     fun getWeather(country:String,units:String) =
        RetrofitInstance.api!!.getWeather(RetrofitInstance.getQueryParams(country, units)!!)



}
