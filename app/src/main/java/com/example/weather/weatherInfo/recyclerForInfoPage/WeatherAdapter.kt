package com.example.weather.mainActivity

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.weather.R
import com.example.weather.weatherInfo.recyclerForInfoPage.TemperatureDataModel
import com.example.weather.weatherInfo.recyclerForInfoPage.TemperatureHolder
import com.example.weather.weatherInfo.recyclerForInfoPage.WeatherDataHolder
import com.example.weather.weatherInfo.recyclerForInfoPage.WeatherDataModel


class WeatherAdapter(
    private var dataSet: List<Any?>
) : RecyclerView.Adapter<WeatherViewHolder<*>>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherViewHolder<*> {
        Log.e("onCreateViewHolder ", "START")
       return when(viewType){
           TYPE_TEMPERATURE ->{
               TemperatureHolder(LayoutInflater.from(parent.context)
                   .inflate(R.layout.temperature_template, parent, false))

           }
           TYPE_ANOTHER_DATA ->{
               WeatherDataHolder(LayoutInflater.from(parent.context)
                   .inflate(R.layout.another_weather_data_template, parent, false))
           }
           else -> throw IllegalArgumentException("Invalid view type")
       }
    }

    override fun getItemCount(): Int{
        return dataSet.size
    }

    override fun onBindViewHolder(holder: WeatherViewHolder<*>, position: Int) {
        when(holder){
            is TemperatureHolder ->{
                holder.bind(dataSet[position] as TemperatureDataModel)
            }
            is WeatherDataHolder ->{
                holder.bind(dataSet[position] as WeatherDataModel)
            }
            else -> throw IllegalArgumentException("invalid holder type")
        }
    }


    override fun getItemViewType(position: Int): Int {
        return when (dataSet[position]) {
            is TemperatureDataModel -> TYPE_TEMPERATURE
            is WeatherDataModel -> TYPE_ANOTHER_DATA
            else -> throw IllegalArgumentException("Invalid type of data " + position)
        }
    }

    companion object {
        private const val TYPE_TEMPERATURE = 0
        private const val TYPE_ANOTHER_DATA = 1
    }

}


