package com.example.weather

import com.example.weather.response.NearestCity
import com.example.weather.response.WeatherResponse

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherService {


    @GET("weather")
    fun weatherByName(@Query("q") name: String): Single<WeatherResponse>

    @GET("weather")
    fun weatherById(@Query("id") id: String): Single<WeatherResponse>

    @GET("find")
    fun citiesInCicle(
        @Query("lat") lat: Double, @Query("lon") lon: Double,
        @Query("cnt") cnt: Int
    ): Single<NearestCity>
}
