package com.example.myhotels.domain

import com.example.myhotels.domain.model.HotelDetailItem
import com.example.myhotels.domain.model.HotelEntity

interface HotelsRepository {

    suspend fun getDetailHotelItem(id: Int): HotelDetailItem

    suspend fun loadHotelsDataFromNetwork (query:String): List<HotelEntity>



}
