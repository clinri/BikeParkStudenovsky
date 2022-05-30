package ru.netology.bikeparkstudenovsky.domain

class DeleteBikePartItemUseCase(private val bikePartsListRepository: BikePartsListRepository) {
    fun deleteBikePartItem(bikePartItem: BikePartItem){
        bikePartsListRepository.deleteBikePartItem(bikePartItem)
    }
}