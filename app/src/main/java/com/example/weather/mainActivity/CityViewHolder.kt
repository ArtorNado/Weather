package com.example.weather.mainActivity

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.weather.R
import com.example.weather.response.WeatherResponse
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.city_recycler_template.view.*

class CityViewHolder(
    override val containerView: View,
    private val clickLambda: (WeatherResponse) -> Unit
) : RecyclerView.ViewHolder(containerView), LayoutContainer {

    private var tvCity = containerView.tv_city
    private var tvCityTemperature = containerView.tv_cityTemperature

    fun bind(weather: WeatherResponse) {
        tvCity.text = weather.name
        tvCityTemperature.text = (weather.main.temp).toInt().toString()
        textColor(weather.main.temp)
        itemView.setOnClickListener {
            clickLambda(weather)
        }
    }

    private fun textColor(temp: Double){
        when (temp) {
            in -100..-30 -> tvCityTemperature.setTextColor(Color.rgb(1, 4, 82))
            in -29..-14 -> tvCityTemperature.setTextColor(Color.rgb(0, 73, 156))
            in -14..-7 -> tvCityTemperature.setTextColor(Color.rgb(2, 96, 204))
            in -5..0 -> tvCityTemperature.setTextColor(Color.rgb(0, 115, 247))
            in 1..10 -> tvCityTemperature.setTextColor(Color.rgb(255, 145, 145))
            in 11..18 -> tvCityTemperature.setTextColor(Color.rgb(255, 74, 74))
            in 19..27 -> tvCityTemperature.setTextColor(Color.rgb(255, 3, 3))
            in 28..100 -> tvCityTemperature.setTextColor(Color.rgb(127, 0, 0))
        }
    }
    companion object {

        fun create(parent: ViewGroup, clickLambda: (WeatherResponse) -> Unit) =
            CityViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.city_recycler_template, parent, false),
                clickLambda
            )
    }
}
