package com.example.myhotels.domain.model

data class BookDetailItem(
    val id: Int,
    val name: String,
    val address: String,
    val stars: Double,
    val distance: Double,
    val suitesAvailability: String,
    val image: String,
    val lat: Double,
    val lon: Double
)
