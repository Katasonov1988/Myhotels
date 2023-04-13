package com.example.myhotels.domain

class LoadHotelsDataFromNetworkInteractor(
    private val repository: HotelsRepository
) {
    suspend operator fun invoke(query:String) = repository.loadHotelsDataFromNetwork(query)

}