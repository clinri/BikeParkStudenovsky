package ru.netology.bikeparkstudenovsky.domain

class GetBikePartsListUseCase(private val bikePartsListRepository: BikePartsListRepository) {
    fun getBikePartsList():List<BikePartItem>{
        return bikePartsListRepository.getBikePartsList()
    }
}