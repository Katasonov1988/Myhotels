package com.example.myhotels.data.repository

import android.util.Log
import androidx.lifecycle.LiveData
import com.example.myhotels.data.mapper.toHotelDetailItem
import com.example.myhotels.data.network.ApiService
import com.example.myhotels.domain.HotelsRepository
import com.example.myhotels.domain.model.HotelDetailItem
import com.example.myhotels.domain.model.HotelEntity

class HotelsRepositoryImpl(private val apiService: ApiService): HotelsRepository {
    override fun getHotelList(): LiveData<List<HotelEntity>> {
        TODO("Not yet implemented")
    }

    override suspend fun getDetailHotelItem(id: Int): HotelDetailItem {
        Log.d("Load_data","REPO_CALLED" + apiService.getDetailHotelItem(id).toHotelDetailItem().toString())
        return apiService.getDetailHotelItem(id).toHotelDetailItem()
    }

    override suspend fun loadHoteslData(query: String): LiveData<List<HotelEntity>> {
        val hotelsData = apiService.getFullHotelList(query)

    }

}