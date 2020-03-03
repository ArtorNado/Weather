package com.example.weather.mainActivity

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.weather.R
import com.example.weather.constants.Constants
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
            in Constants.DIGRESS.HARD_FROSTY -> tvCityTemperature.setTextColor(Color.parseColor("#012852"))
            in Constants.DIGRESS.VERY_FROSTY -> tvCityTemperature.setTextColor(Color.parseColor("#00499C"))
            in Constants.DIGRESS.MODERATELY_FROSTY -> tvCityTemperature.setTextColor(Color.parseColor("#02609C"))
            in Constants.DIGRESS.LIGHTLY_FROSTY -> tvCityTemperature.setTextColor(Color.parseColor("#0073F7"))
            in Constants.DIGRESS.COOL -> tvCityTemperature.setTextColor(Color.parseColor("#0073F7"))
            in Constants.DIGRESS.MODERATELY_WARM -> tvCityTemperature.setTextColor(Color.parseColor("#FF4A4A"))
            in Constants.DIGRESS.HOT -> tvCityTemperature.setTextColor(Color.parseColor("#FF0303"))
            in Constants.DIGRESS.EXTREMELY_HOT -> tvCityTemperature.setTextColor(Color.parseColor("#7F0000"))
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
