package com.example.weather.weatherInfo

import com.example.weather.constants.Constants

class Converter {

    companion object {

        fun windDirection(degree: Int?): String {
            return when (degree) {
                in Constants.DIRECTION.NORTH_NORTH_EAST-> "North-north-east"
                in Constants.DIRECTION.SOUTH_EAST -> "South-east"
                in Constants.DIRECTION.EAST_NORTH_EAST -> "East-north-east"
                in Constants.DIRECTION.EAST -> "East"
                in Constants.DIRECTION.EAST_SOUTH_EAST -> "East-south-east"
                in Constants.DIRECTION.SOUTH_EAST2 -> "South-east"
                in Constants.DIRECTION.SOUTH_SOUTH_EAST -> "South-south-east"
                in Constants.DIRECTION.EAST2 -> "East"
                in Constants.DIRECTION.SOUTH_SOUTH_WEST -> "South-south-west"
                in Constants.DIRECTION.SOUTH_WEST -> "South-west"
                in Constants.DIRECTION.WEST_SOUTH_WEST -> "West-south-west"
                in Constants.DIRECTION.WEST -> "West"
                in Constants.DIRECTION.WEST_NORTH_WEST -> "West-north-west"
                in Constants.DIRECTION.NORTH_WEST -> "North-west"
                in Constants.DIRECTION.NORTH_NORTH_WEST -> "North-north-west"
                in Constants.DIRECTION.NORTH2 -> "North"
                in Constants.DIRECTION.NORTH -> "North"
                else -> "Calm"
            }
        }
    }
}
