package com.example.weather.cityInfroActivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.weather.ApiFactory
import com.example.weather.R
import com.example.weather.WeatherService
import com.example.weather.mainActivity.WeatherAdapter
import com.example.weather.response.WeatherResponse
import com.example.weather.cityInfroActivity.recyclerForInfoPage.TemperatureDataModel
import com.example.weather.cityInfroActivity.recyclerForInfoPage.WeatherDataModel
import kotlinx.android.synthetic.main.activity_info.*
import kotlinx.coroutines.*

class InfoActivity : AppCompatActivity(), CoroutineScope by MainScope() {

    private var adapter: WeatherAdapter? = null
    private var cityId: String? = null
    private lateinit var service: WeatherService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_info)
        val arguments: Bundle = intent.extras
        cityId = arguments["city id"]?.toString()
        cityId?.let { getCityInfoById(it) }
    }

    private fun setMainInformation(weather: WeatherResponse?){
        tv_cityName.text = weather?.name
        tv_weather.text = weather?.weathers?.get(0)?.description
        if(thunderWeather.contains(weather?.weathers?.get(0)?.main.toString()))
            info_main_layout.setBackgroundResource(R.drawable.thunder)
        if(rainWeather.contains(weather?.weathers?.get(0)?.main.toString()))
            info_main_layout.setBackgroundResource(R.drawable.rain)
        if(cloudy.contains(weather?.weathers?.get(0)?.main.toString()))
            info_main_layout.setBackgroundResource(R.drawable.cloudy_day)
        if(sunny.contains(weather?.weathers?.get(0)?.main.toString()))
            info_main_layout.setBackgroundResource(R.drawable.sunny)
    }

    private fun getCityInfoById(cityId : String){
        service = ApiFactory.weatherService
        launch {
            val response = withContext(Dispatchers.IO) {
                service.weatherById(cityId)
            }
            if (response.isSuccessful) {
                setMainInformation(response.body())
                setAdapter(response.body())
            } else {
                Toast.makeText(this@InfoActivity, "U TEBYA BEDI S BASHKOY", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }
    private fun setAdapter(weather: WeatherResponse?) {
        var list  = listOf(
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

    companion object{
        var thunderWeather = listOf("Thunderstorm")
        var rainWeather = listOf("Drizzle", "Rain")
        var cloudy = listOf("Mist", "Smoke", "Haze", "Dust", "Fog", "Sand", "Dust",
            "Ash", "Squall", "Tornado", "Clouds")
        var sunny = listOf("Clear")
    }
}
