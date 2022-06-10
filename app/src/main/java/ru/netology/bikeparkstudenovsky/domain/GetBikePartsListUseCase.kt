package ru.netology.bikeparkstudenovsky.domain

import androidx.lifecycle.LiveData

class GetBikePartsListUseCase(
    private val bikePartsListRepository: BikePartsListRepository
    ) {
    fun getBikePartsList(): LiveData<List<BikePartItem>> {
        return bikePartsListRepository.getBikePartsList()
    }
}