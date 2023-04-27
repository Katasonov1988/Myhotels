package com.example.myhotels.ui.hotelDetailScreen

import android.util.Log
import androidx.lifecycle.*
import com.example.myhotels.data.repository.HotelsRepositoryImpl
import com.example.myhotels.domain.GetHotelDetailInteractor
import com.example.myhotels.domain.model.HotelDetailItem
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

class HotelDetailViewModel(
    private val repository: HotelsRepositoryImpl
) : ViewModel() {
    private val getDetailHotelInteractor = GetHotelDetailInteractor(repository)

    private val _hotelItem = MutableLiveData<HotelDetailItem>()
    val hotelItem: LiveData<HotelDetailItem>
        get() = _hotelItem

    private val _currentSort = MutableLiveData<String>()
    val currentSort: LiveData<String> = _currentSort

    fun getHotelDetailItem(hotelId: Int) {
        viewModelScope.launch {
            try {
                val item = getDetailHotelInteractor.invoke(hotelId)

                _hotelItem.value = item
            } catch (e: IOException) {
                Log.d("HotelItemInfo", "IOException response $e")
            } catch (e: HttpException) {
                Log.d("HotelItemInfo", "HttpException response $e")
            }
        }
    }
}