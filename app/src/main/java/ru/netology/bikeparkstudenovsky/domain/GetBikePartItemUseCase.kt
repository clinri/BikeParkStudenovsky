package ru.netology.bikeparkstudenovsky.domain

class GetBikePartItemUseCase(private val bikePartsListRepository: BikePartsListRepository) {
    fun getBikePartItem(bikePartItemId: Int): BikePartItem{
        return bikePartsListRepository.getBikePartItem(bikePartItemId)
    }
}