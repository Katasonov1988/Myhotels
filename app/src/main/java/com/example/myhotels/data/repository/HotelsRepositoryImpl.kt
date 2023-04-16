package com.example.myhotels.data.repository

import com.example.myhotels.data.mapper.HotelMapper
import com.example.myhotels.data.mapper.toHotelDetailItem
import com.example.myhotels.data.network.ApiService
import com.example.myhotels.data.network.model.HotelData
import com.example.myhotels.domain.HotelsRepository
import com.example.myhotels.domain.model.HotelDetailItem
import com.example.myhotels.domain.model.HotelEntity

class HotelsRepositoryImpl(private val apiService: ApiService) : HotelsRepository {
    private val mapper = HotelMapper()
    override suspend fun getDetailHotelItem(id: Int): HotelDetailItem {
        return apiService.getDetailHotelItem(id).toHotelDetailItem()
    }

    override suspend fun loadHotelsDataFromNetwork(query: String): List<HotelEntity> {
        var hotelsDataFromNetwork: List<HotelData> = listOf()
        try {
            hotelsDataFromNetwork = apiService.getFullHotelList(query)
        } catch (_: Exception) {
        }
        return hotelsDataFromNetwork.map {
            mapper.mapHotelDataToHotelEntity(it)
        }
    }
}