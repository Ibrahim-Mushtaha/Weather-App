package com.ix.ibrahim7.weatherapp.ui.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.ix.ibrahim7.weatherapp.model.Weather
import com.ix.ibrahim7.weatherapp.repository.ApiRepository
import com.ix.ibrahim7.weatherapp.util.Resource
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.ObservableOnSubscribe
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import io.reactivex.rxjava3.schedulers.Schedulers.io
import java.util.concurrent.TimeUnit

class HomeViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = ApiRepository()
    val dataWeatherLiveData = MutableLiveData<Resource<Weather>>()
    private val compositeDisposable = CompositeDisposable()
    var data: Weather? = null



    fun getWeather(country:String) {
        dataWeatherLiveData.postValue(Resource.Loading())
        val observable = repository.getWeather(country)
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




    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }



}