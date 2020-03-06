package com.example.weather.cityInfroActivity.recyclerForInfoPage

import android.view.View
import com.example.weather.mainActivity.WeatherViewHolder
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.another_weather_data_template.view.*

class WeatherDataHolder(
    override val containerView: View
) : WeatherViewHolder<WeatherDataModel>(containerView), LayoutContainer {

    var tvData = containerView.tv_data
    var tvDataType = containerView.tv_dataType

    override fun bind(weather: WeatherDataModel) {
        tvData.text = weather.data
        tvDataType.text = weather.dataType
    }

}
