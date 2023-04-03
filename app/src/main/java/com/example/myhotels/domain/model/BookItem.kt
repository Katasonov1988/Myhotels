package com.example.myhotels.domain.model

data class BookItem(
    val id: Int,
    val name: String,
    val address: String,
    val stars: Double,
    val distance: Double,
    val suitesAvailability: String
)

