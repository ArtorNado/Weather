package com.example.weather.mainActivity

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.weather.ApiFactory
import com.example.weather.WeatherService
import com.example.weather.constants.Constants
import com.example.weather.response.WeatherResponse
import kotlinx.coroutines.*


class MainViewModel(private var latitude: Double, private var longitude: Double) : ViewModel(),
    CoroutineScope by MainScope() {

    val cityList: MutableLiveData<List<WeatherResponse>> by lazy { MutableLiveData<List<WeatherResponse>>() }
    private var service: WeatherService = ApiFactory.weatherService

    init {
        launch {
            val response = withContext(Dispatchers.IO) {
                service.citiesInCicle(latitude, longitude, Constants.NEARCITY.CITY_COUNT)
            }
            if (response.isSuccessful) {
                cityList.value = response.body()?.list
            }
        }
    }

}
