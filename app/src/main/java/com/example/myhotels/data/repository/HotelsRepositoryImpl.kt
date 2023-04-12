package com.example.myhotels.data.repository

import androidx.lifecycle.*
import com.example.myhotels.data.mapper.HotelMapper
import com.example.myhotels.data.mapper.toHotelDetailItem
import com.example.myhotels.data.network.ApiService
import com.example.myhotels.data.network.model.HotelData
import com.example.myhotels.domain.HotelsRepository
import com.example.myhotels.domain.model.HotelDetailItem
import com.example.myhotels.domain.model.HotelEntity

class HotelsRepositoryImpl(private val apiService: ApiService) : HotelsRepository {
    private val mapper = HotelMapper()
    private val hotelsDataFromNetwork = MutableLiveData<List<HotelData>>()


    override fun getHotelList(): LiveData<List<HotelEntity>> {
        return MediatorLiveData<List<HotelEntity>>().apply {
            addSource(hotelsDataFromNetwork) {
                it.map {
                    mapper.mapHotelDataToHotelEntity(it)
                }
            }
        }
    }

    override suspend fun getDetailHotelItem(id: Int): HotelDetailItem {
        return apiService.getDetailHotelItem(id).toHotelDetailItem()
    }

    override suspend fun loadHotelsData(query: String) {
        hotelsDataFromNetwork.value = apiService.getFullHotelList(query)

    }

}