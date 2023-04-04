package com.example.myhotels.domain

class LoadDataFromNetworkInteractor(
    private val repository: HotelsRepository
) {

    suspend operator fun invoke() = repository.loadData()

}