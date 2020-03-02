package com.example.weather.weatherInfo.recyclerForInfoPage

import android.view.View
import com.example.weather.mainActivity.WeatherViewHolder
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.temperature_template.view.*

class TemperatureHolder(
    override val containerView: View
) : WeatherViewHolder<TemperatureDataModel>(containerView), LayoutContainer {

    var tvTemperature = containerView.tv_temperature

    override fun bind(weather: TemperatureDataModel) {
        tvTemperature.text = weather.temperature
    }

}
