package com.example.weather.mainActivity

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.weather.response.WeatherResponse

class CityAdapter(
    private var city: List<WeatherResponse>,
    private val clickLambda: (WeatherResponse) -> Unit
) : RecyclerView.Adapter<CityViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityViewHolder =
        CityViewHolder.create(parent, clickLambda)


    override fun getItemCount(): Int = city.size


    override fun onBindViewHolder(holder: CityViewHolder, position: Int) {
        holder.bind(city[position])
    }

    override fun getItemViewType(position: Int): Int {
        return super.getItemViewType(position)
    }


}

