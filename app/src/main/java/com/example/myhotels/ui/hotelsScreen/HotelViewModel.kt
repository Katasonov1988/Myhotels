package com.example.myhotels.ui.hotelsScreen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myhotels.data.repository.HotelsRepositoryImpl
import com.example.myhotels.domain.LoadHotelsDataFromNetworkInteractor
import com.example.myhotels.domain.model.HotelEntity
import com.example.myhotels.ui.sort_button_sheet_fragment.DEFAULT_SORT
import com.example.myhotels.ui.sort_button_sheet_fragment.DISTANCE_SORT
import com.example.myhotels.ui.sort_button_sheet_fragment.NUMBER_FREE_ROOMS_SORT
import kotlinx.coroutines.launch

class HotelViewModel(
    private val repository: HotelsRepositoryImpl
) : ViewModel() {
    private val loadHotelsDataFromNetworkInteractor =
        LoadHotelsDataFromNetworkInteractor(repository)

    private var hotelsFromNetwork: List<HotelEntity> = listOf()

    private val _hotels = MutableLiveData<List<HotelEntity>?>()
    val hotels: LiveData<List<HotelEntity>?> = _hotels

    fun getHotelsDataFromNetwork(query: String) {
        viewModelScope.launch {
            hotelsFromNetwork = loadHotelsDataFromNetworkInteractor(query)
            _hotels.value = hotelsFromNetwork.sortedBy { it.name }
        }
    }

    fun resetOrder() {
        _hotels.value = null
    }

    fun sortHotels(sortRequest: String) {
        when (sortRequest) {
            DEFAULT_SORT -> {
                _hotels.value = hotelsFromNetwork.sortedBy { it.name }
            }
            DISTANCE_SORT -> {
                _hotels.value = hotelsFromNetwork.sortedBy { it.distance }
            }
            NUMBER_FREE_ROOMS_SORT -> {
                _hotels.value = hotelsFromNetwork.sortedBy { it.suitesAvailability }
            }
        }
    }

}