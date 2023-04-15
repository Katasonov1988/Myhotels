package com.example.myhotels.domain.model

data class HotelDetailItem(
    val id: Int,
    val name: String,
    val address: String,
    val stars: Int,
    val distance: String,
    val suitesAvailability: String,
    val image: String,
    val lat: Double,
    val lon: Double
)
