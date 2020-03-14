package com.example.weather

import com.example.weather.response.NearestCity
import com.example.weather.response.WeatherResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherService {


    @GET("weather")
    suspend fun weatherByName(@Query("q") name: String): Response<WeatherResponse>

    @GET("weather")
    suspend fun weatherById(@Query("id") id: String): Response<WeatherResponse>

    @GET("find")
    suspend fun citiesInCicle(@Query("lat") lat: Double, @Query("lon") lon: Double,
                              @Query("cnt") cnt: Int): Response<NearestCity>
}
