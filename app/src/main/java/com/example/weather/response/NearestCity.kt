package com.example.weather.response

import com.google.gson.annotations.SerializedName

data class NearestCity(
    @SerializedName("list")
    var list: List<WeatherResponse>? = null
)
