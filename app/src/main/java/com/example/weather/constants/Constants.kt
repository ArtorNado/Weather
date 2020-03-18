package com.example.weather.constants

class Constants {
    interface DIRECTION {
        companion object {
            val NORTH = 0..4
            val NORTH_NORTH_EAST = 5..24
            val SOUTH_EAST = 25..54
            val EAST_NORTH_EAST = 55..74
            val EAST = 75..94
            val EAST_SOUTH_EAST = 95..114
            val SOUTH_EAST2 = 115..144
            val SOUTH_SOUTH_EAST = 145..164
            val EAST2 = 165..184
            val SOUTH_SOUTH_WEST = 185..204
            val SOUTH_WEST = 205..234
            val WEST_SOUTH_WEST = 235..254
            val WEST = 255..274
            val WEST_NORTH_WEST = 275..304
            val NORTH_WEST = 305..324
            val NORTH_NORTH_WEST = 325..344
            val NORTH2 = 345..360
        }
    }

    interface DIGRESS {
        companion object {
            val HARD_FROSTY = -100..-30
            val VERY_FROSTY = -29..-14
            val MODERATELY_FROSTY = -14..-7
            val LIGHTLY_FROSTY = -5..0
            val COOL = 1..10
            val MODERATELY_WARM = 11..18
            val HOT = 19..27
            val EXTREMELY_HOT = 28..100
        }
    }

    interface COORDINATES {
        companion object {
            const val DEFAULT_LATITUDE = 56.0
            const val DEFAULT_LONGITUDE = 45.0
        }
    }

    interface NEARCITY {
        companion object {
            const val CITY_COUNT = 10
        }
    }

    interface REQUESTS {
        companion object {
            const val REQUEST_CODE = 1000
        }
    }
}
