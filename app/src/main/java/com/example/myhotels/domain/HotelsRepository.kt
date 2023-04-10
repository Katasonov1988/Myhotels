package com.example.myhotels.domain

import androidx.lifecycle.LiveData
import com.example.myhotels.domain.model.HotelDetailItem
import com.example.myhotels.domain.model.HotelEntity

interface HotelsRepository {

    fun getHotelList(): LiveData<List<HotelEntity>>

    suspend fun getDetailHotelItem(id: Int): HotelDetailItem

    suspend fun loadHoteslData(query:String):LiveData<List<HotelEntity>>



}
