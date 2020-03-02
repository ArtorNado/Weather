package com.example.weather.weatherInfo

class Converter {

    companion object {

        fun windDirection(degree: Int?): String {
            return when (degree) {
                in 5..24 -> "North-north-east"
                in 25..54 -> "South-east"
                in 55..74 -> "East-north-east"
                in 75..94 -> "East"
                in 95..114 -> "East-south-east"
                in 115..144 -> "South-east"
                in 145..164 -> "South-south-east"
                in 165..184 -> "East"
                in 185..204 -> "South-south-west"
                in 205..234 -> "South-west"
                in 235..254 -> "West-south-west"
                in 255..274 -> "West"
                in 275..304 -> "West-north-west"
                in 305..324 -> "North-west"
                in 325..344 -> "North-north-west"
                in 355..360 -> "North"
                in 0..4 -> "North"
                else -> "Calm"
            }
        }
    }
}
