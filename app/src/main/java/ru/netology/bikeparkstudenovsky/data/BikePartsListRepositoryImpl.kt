package ru.netology.bikeparkstudenovsky.data

import ru.netology.bikeparkstudenovsky.domain.BikePartItem
import ru.netology.bikeparkstudenovsky.domain.BikePartsListRepository
import java.lang.RuntimeException

object BikePartsListRepositoryImpl:BikePartsListRepository {

    private val bikePartsList = mutableListOf<BikePartItem>()
    private var autoIncrementId = 0

    override fun addBikePartItem(bikePartItem: BikePartItem) {
        if (bikePartItem.id == BikePartItem.UNDEFINED_ID) {
            bikePartItem.id = autoIncrementId++
        }
        bikePartsList.add(bikePartItem)
    }

    override fun deleteBikePartItem(bikePartItem: BikePartItem) {
        bikePartsList.remove(bikePartItem)
    }

    override fun editBitePartItem(bikePartItem: BikePartItem) {
        val oldElement = getBikePartItem(bikePartItem.id)
        bikePartsList.remove(oldElement)
        bikePartsList.add(bikePartItem)
    }

    override fun getBikePartItem(bikePartItemId: Int): BikePartItem {
        return bikePartsList.find {
            it.id == bikePartItemId
        } ?: throw RuntimeException("Element with id $bikePartItemId not found")
    }

    override fun getBikePartsList(): List<BikePartItem> {
        return bikePartsList.toList()
    }
}