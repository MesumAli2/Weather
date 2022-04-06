package com.mesum.weather.model

data class WeatherModel(
    val current: Current,
    val location: Location,
    val request: Request
)