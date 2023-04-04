package com.example.myhotels.domain

import androidx.lifecycle.LiveData
import com.example.myhotels.domain.model.HotelDetailItem
import com.example.myhotels.domain.model.HotelList

interface HotelsRepository {

    fun getHotelList(): LiveData<List<HotelList>>

    suspend fun getDetailHotelItem(id: Int): HotelDetailItem

    suspend fun loadData()



}