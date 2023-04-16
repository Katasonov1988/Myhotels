package com.example.myhotels.ui.hotelsScreen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myhotels.data.repository.HotelsRepositoryImpl
import com.example.myhotels.domain.LoadHotelsDataFromNetworkInteractor
import com.example.myhotels.domain.model.HotelEntity
import kotlinx.coroutines.launch

class HotelViewModel(
    private val repository: HotelsRepositoryImpl
) : ViewModel() {
    private val loadHotelsDataFromNetworkInteractor =
        LoadHotelsDataFromNetworkInteractor(repository)

    private val _hotels = MutableLiveData<List<HotelEntity>?>()
    val hotels: LiveData<List<HotelEntity>?> = _hotels

    fun getHotelsDataFromNetwork(query: String) {
        viewModelScope.launch {
            _hotels.value = loadHotelsDataFromNetworkInteractor(query)
        }
    }

    fun resetOrder() {
        _hotels.value = null
    }

}