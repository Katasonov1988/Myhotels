package com.example.myhotels.data.network

import com.example.myhotels.data.network.model.HotelDetailItemData
import com.example.myhotels.data.network.model.HotelData
import com.example.myhotels.domain.model.HotelEntity
import retrofit2.http.GET
import retrofit2.http.Path

//    получить весь список
//    https://raw.githubusercontent.com/iMofas/ios-android-test/master/0777.json

//    получить определённый отель
//    https://raw.githubusercontent.com/iMofas/ios-android-test/master/N.json
//    где N - id отеля

//    получить картинку
//    https://github.com/iMofas/ios-android-test/raw/master/M.jpg
//    где М - id картинки из метода ПОЛУЧИТЬ ОПРЕДЕЛЕННЫЙ ОТЕЛЬ

interface ApiService {
    companion object {
        private const val KEY_PARAM = "keyOfListHotel"
        private const val KEY = "0777"
        private const val HOTEL_ID = "hotelId"
    }

    @GET("ios-android-test/master/{keyOfListHotel}.json")
    suspend fun getFullHotelList(
        @Path(KEY_PARAM) keyOfListHotel: String = KEY
    ): List<HotelData>


    @GET("ios-android-test/master/{hotelId}.json")
    suspend fun getDetailHotelItem(
        @Path(HOTEL_ID) hotelId: Int
    ): HotelDetailItemData
}