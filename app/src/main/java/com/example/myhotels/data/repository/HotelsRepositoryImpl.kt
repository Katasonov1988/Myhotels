package com.example.myhotels.data.repository

import androidx.lifecycle.LiveData
import com.example.myhotels.domain.HotelsRepository
import com.example.myhotels.domain.model.HotelDetailItem
import com.example.myhotels.domain.model.HotelList

class HotelsRepositoryImpl(): HotelsRepository {
    override fun getHotelList(): LiveData<List<HotelList>> {
        TODO("Not yet implemented")
    }

    override suspend fun getDetailHotelItem(id: Int): HotelDetailItem {
        TODO("Not yet implemented")
    }

    override suspend fun loadData() {
       while (true) {

       }
    }
}