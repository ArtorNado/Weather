package com.example.weather.cityInfroActivity

import com.example.weather.constants.Constants

object Converter {

    fun windDirection(degree: Int?): String {
        return when (degree) {
            in Constants.DIRECTION.NORTH_NORTH_EAST -> "North-north-east"
            in Constants.DIRECTION.SOUTH_EAST -> "South-east"
            in Constants.DIRECTION.EAST_NORTH_EAST -> "East-north-east"
            in Constants.DIRECTION.EAST -> "East"
            in Constants.DIRECTION.EAST_SOUTH_EAST -> "East-south-east"
            in Constants.DIRECTION.SOUTH_EAST2 -> "South-east"
            in Constants.DIRECTION.SOUTH_SOUTH_EAST -> "South-south-east"
            else -> "Calm"
        }
    }
}
