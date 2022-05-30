package ru.netology.bikeparkstudenovsky.domain

interface BikePartsListRepository {
    fun addBikePartItem(bikePartItem: BikePartItem)
    fun deleteBikePartItem(bikePartItem: BikePartItem)
    fun editBitePartItem(bikePartItem: BikePartItem)
    fun getBikePartItem(bikePartItemId: Int): BikePartItem
    fun getBikePartsList():List<BikePartItem>
}