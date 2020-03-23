package com.example.weather.cityInfroActivity

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.weather.ApiFactory
import com.example.weather.R
import com.example.weather.WeatherService
import com.example.weather.cityInfroActivity.recyclerForInfoPage.TemperatureDataModel
import com.example.weather.cityInfroActivity.recyclerForInfoPage.WeatherDataModel
import com.example.weather.mainActivity.WeatherAdapter
import com.example.weather.response.WeatherResponse
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_info.*

class InfoActivity : AppCompatActivity() {

    private var adapter: WeatherAdapter? = null
    private var cityId: String? = null
    private var service: WeatherService = ApiFactory.weatherService
    private var disposable: Disposable? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_info)
        val arguments: Bundle = intent.extras
        cityId = arguments["city id"]?.toString()
        cityId?.let { getCityInfoById(it) }
    }

    override fun onDestroy() {
        super.onDestroy()
        if (disposable?.isDisposed == false) disposable?.dispose()
    }

    private fun setMainInformation(weather: WeatherResponse?) {
        tv_cityName.text = weather?.name
        tv_weather.text = weather?.weathers?.get(0)?.description
        if (thunderWeather.contains(weather?.weathers?.get(0)?.main.toString()))
            info_main_layout.setBackgroundResource(R.drawable.thunder)
        if (rainWeather.contains(weather?.weathers?.get(0)?.main.toString()))
            info_main_layout.setBackgroundResource(R.drawable.rain)
        if (cloudy.contains(weather?.weathers?.get(0)?.main.toString()))
            info_main_layout.setBackgroundResource(R.drawable.cloudy_day)
        if (sunny.contains(weather?.weathers?.get(0)?.main.toString()))
            info_main_layout.setBackgroundResource(R.drawable.sunny)
    }

    private fun getCityInfoById(cityId: String) {
        disposable = service.weatherById(cityId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ result -> fillupPage(result) },
                { error ->
                    Toast.makeText(this@InfoActivity, "U TEBYA BEDI S BASHKOY", Toast.LENGTH_SHORT)
                        .show()
                })

    }

    fun fillupPage(weatherResponse: WeatherResponse) {
        setMainInformation(weatherResponse)
        setAdapter(weatherResponse)
    }

    private fun setAdapter(weather: WeatherResponse?) {
        val list = listOf(
            TemperatureDataModel(weather?.main?.temp?.toInt().toString()),
            WeatherDataModel(weather?.main?.feelsLike?.toInt().toString(), "feels like"),
            WeatherDataModel(weather?.main?.pressure.toString(), "pressure"),
            WeatherDataModel(weather?.main?.humidity.toString(), "humidity"),
            WeatherDataModel(Converter.windDirection(weather?.wind?.deg), "direction of the wind"),
            WeatherDataModel(weather?.wind?.speed.toString(), "wind speed"),
            WeatherDataModel(weather?.main?.tempMax.toString(), "max temperature"),
            WeatherDataModel(weather?.main?.tempMin.toString(), "min temperature")
        )
        adapter = WeatherAdapter(list)
        rv_data.adapter = adapter
    }

    companion object {
        var thunderWeather = listOf("Thunderstorm")
        var rainWeather = listOf("Drizzle", "Rain")
        var cloudy = listOf(
            "Mist", "Smoke", "Haze", "Dust", "Fog", "Sand", "Dust",
            "Ash", "Squall", "Tornado", "Clouds"
        )
        var sunny = listOf("Clear")
    }
}
