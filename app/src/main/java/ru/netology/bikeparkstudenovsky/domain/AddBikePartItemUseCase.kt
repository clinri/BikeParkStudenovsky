package ru.netology.bikeparkstudenovsky.domain

class AddBikePartItemUseCase(private val bikePartsListRepository: BikePartsListRepository) {
    fun addBikePartItem(bikePartItem: BikePartItem){
        bikePartsListRepository.addBikePartItem(bikePartItem)
    }
}