package com.example.myhotels.ui.hotelsScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import com.example.myhotels.data.repository.HotelsRepositoryImpl

class HotelViewModelFactory(
    private val repository: HotelsRepositoryImpl
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(
        modelClass: Class<T>,
        extras: CreationExtras
    ): T {
        if (modelClass.isAssignableFrom(HotelViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return HotelViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}