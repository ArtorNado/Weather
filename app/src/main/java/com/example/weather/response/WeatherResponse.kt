package com.example.weather.response

import com.google.gson.annotations.SerializedName

data class WeatherResponse(
    @SerializedName("base")
    var base: String,
    @SerializedName("clouds")
    var clouds: Clouds,
    @SerializedName("cod")
    var cod: Int,
    @SerializedName("coord")
    var coord: Coord,
    @SerializedName("dt")
    var dt: Int,
    @SerializedName("id")
    var id: Int,
    @SerializedName("main")
    var main: Main,
    @SerializedName("name")
    var name: String,
    @SerializedName("sys")
    var sys: Sys,
    @SerializedName("visibility")
    var visibility: Int,
    @SerializedName("weather")
    var weathers: List<Weather>,
    @SerializedName("wind")
    var wind: Wind
)

data class Clouds(
    @SerializedName("all")
    var all: Int
)

data class Coord(
    @SerializedName("lat")
    var lat: Double,
    @SerializedName("lon")
    var lon: Double
)

data class Main(
    @SerializedName("humidity")
    var humidity: Int,
    @SerializedName("pressure")
    var pressure: Int,
    @SerializedName("temp")
    var temp: Double,
    @SerializedName("temp_max")
    var tempMax: Double,
    @SerializedName("temp_min")
    var tempMin: Double,
    @SerializedName("feels_like")
    var feelsLike: Double
)

data class Sys(
    @SerializedName("country")
    var country: String,
    @SerializedName("id")
    var id: Int,
    @SerializedName("message")
    var message: Double,
    @SerializedName("sunrise")
    var sunrise: Int,
    @SerializedName("sunset")
    var sunset: Int,
    @SerializedName("type")
    var type: Int
)

data class Weather(
    @SerializedName("description")
    var description: String,
    @SerializedName("icon")
    var icon: String,
    @SerializedName("id")
    var id: Int,
    @SerializedName("main")
    var main: String
)

data class Wind(
    @SerializedName("deg")
    var deg: Int,
    @SerializedName("speed")
    var speed: Double
)
