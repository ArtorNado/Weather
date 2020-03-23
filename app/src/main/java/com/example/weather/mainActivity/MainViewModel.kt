package com.example.weather.mainActivity

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.weather.ApiFactory
import com.example.weather.WeatherService
import com.example.weather.constants.Constants
import com.example.weather.response.WeatherResponse
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MainViewModel(private var latitude: Double, private var longitude: Double) : ViewModel() {

    val cityList: MutableLiveData<List<WeatherResponse>> by lazy { MutableLiveData<List<WeatherResponse>>() }
    private var service: WeatherService = ApiFactory.weatherService

    init {
        service.citiesInCicle(latitude, longitude, Constants.NEARCITY.CITY_COUNT)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { result -> cityList.value = result.list }
    }

}
