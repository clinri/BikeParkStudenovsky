package ru.netology.bikeparkstudenovsky.domain

class EditBikePartItemUseCase(private val bikePartsListRepository: BikePartsListRepository) {
    fun editBitePartItem(bikePartItem: BikePartItem) {
        bikePartsListRepository.editBitePartItem(bikePartItem)
    }
}