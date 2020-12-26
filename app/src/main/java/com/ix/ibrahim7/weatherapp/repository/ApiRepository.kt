package com.ix.ibrahim7.weatherapp.repository

import com.ix.ibrahim7.weatherapp.network.RetrofitInstance


class ApiRepository {

     fun getWeather(country: String) =
        RetrofitInstance.api!!.getWeather(country = country)



}
