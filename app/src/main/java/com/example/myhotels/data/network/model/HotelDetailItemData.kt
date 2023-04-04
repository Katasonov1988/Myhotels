package com.example.myhotels.data.network.model

import com.google.gson.annotations.SerializedName

data class HotelDetailItemData(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("address") val address: String,
    @SerializedName("stars") val stars: Double,
    @SerializedName("distance") val distance: Double,
    @SerializedName("suites_availability") val suitesAvailability: String,
    @SerializedName("image") val image: String,
    @SerializedName("lat") val lat: Double,
    @SerializedName("lon") val lon: Double
)