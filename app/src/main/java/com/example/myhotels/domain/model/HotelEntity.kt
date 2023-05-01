package com.example.myhotels.domain.model

data class HotelEntity(
    val id: Int,
    val name: String,
    val address: String,
    val stars: Int,
    val distance: Double,
    val suitesAvailability: Int
)

