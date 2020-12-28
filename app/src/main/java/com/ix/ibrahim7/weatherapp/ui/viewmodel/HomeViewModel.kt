package com.ix.ibrahim7.weatherapp.ui.viewmodel

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.ix.ibrahim7.weatherapp.model.city.CityItem
import com.ix.ibrahim7.weatherapp.model.weather.Weather
import com.ix.ibrahim7.weatherapp.repository.ApiRepository
import com.ix.ibrahim7.weatherapp.util.Constant
import com.ix.ibrahim7.weatherapp.util.Constant.FILENAMAE
import com.ix.ibrahim7.weatherapp.util.Resource
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class HomeViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = ApiRepository()
    val dataWeatherLiveData = MutableLiveData<Resource<Weather>>()
    val dataCityLiveData = MutableLiveData<Resource<List<CityItem>>>()
    private val compositeDisposable = CompositeDisposable()
    var data: Weather? = null



    fun getWeather(country:String,units:String) {
        dataWeatherLiveData.postValue(Resource.Loading())
        val observable = repository.getWeather(country,units)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {c->
                    dataWeatherLiveData.postValue(Resource.Success(c))
                },
                {x->
                    dataWeatherLiveData.postValue(Resource.Error(x.message.toString(),null))
                })

        compositeDisposable.add(observable)
    }



    fun getCity(context: Context,fileName:String){
        dataCityLiveData.postValue(Resource.Loading())
        GlobalScope.launch {
            val json = Constant.getJsonDataFromAsset(context, fileName)
            val gson = Gson()
            val listCityType = object : TypeToken<List<CityItem>>() {}.type
            val citylist: List<CityItem> = gson.fromJson(json.toString(), listCityType)
            dataCityLiveData.postValue(Resource.Success(citylist))
        }
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }


    init {
        getCity(application,FILENAMAE)
    }


}