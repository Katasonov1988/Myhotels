package com.example.myhotels.domain

class GetHotelDetailInteractor(
    private val repository: HotelsRepository
) {
    suspend operator fun invoke(id: Int) = repository.getDetailHotelItem(id)
}