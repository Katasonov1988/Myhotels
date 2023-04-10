package com.example.myhotels.ui.hotelDetailScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import com.example.myhotels.data.repository.HotelsRepositoryImpl

class HotelDetailViewModelFactory(
    private val repository: HotelsRepositoryImpl
):ViewModelProvider.Factory {
    override fun <T : ViewModel> create(
        modelClass: Class<T>,
        extras: CreationExtras
    ): T {
        if (modelClass.isAssignableFrom(HotelDetailViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return HotelDetailViewModel (repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}