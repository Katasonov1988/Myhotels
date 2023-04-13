package com.example.myhotels

import androidx.lifecycle.ViewModelProvider
import com.example.myhotels.data.network.ApiFactory
import com.example.myhotels.data.network.ApiFactory.apiService
import com.example.myhotels.data.network.ApiService
import com.example.myhotels.data.repository.HotelsRepositoryImpl
import com.example.myhotels.ui.hotelDetailScreen.HotelDetailViewModel
import com.example.myhotels.ui.hotelDetailScreen.HotelDetailViewModelFactory
import com.example.myhotels.ui.hotelsScreen.HotelViewModelFactory

object Injection {

    private fun provideHotelRepository(): HotelsRepositoryImpl{
        return HotelsRepositoryImpl(ApiFactory.apiService)
    }

    fun provideHotelViewModelFactory(): ViewModelProvider.Factory{
        return HotelViewModelFactory(provideHotelRepository())
    }

    fun provideHotelDetailViewModelFactory(): ViewModelProvider.Factory{
        return HotelDetailViewModelFactory(provideHotelRepository())
    }
}