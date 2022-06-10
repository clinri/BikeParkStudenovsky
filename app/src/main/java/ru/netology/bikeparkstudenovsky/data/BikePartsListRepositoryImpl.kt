package ru.netology.bikeparkstudenovsky.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ru.netology.bikeparkstudenovsky.domain.BikePartItem
import ru.netology.bikeparkstudenovsky.domain.BikePartsListRepository
import java.lang.RuntimeException

object BikePartsListRepositoryImpl : BikePartsListRepository {

    private val bikePartsListLD = MutableLiveData<List<BikePartItem>>()
    private val bikePartsList = mutableListOf<BikePartItem>()
    private var autoIncrementId = 0

    init {
        for (i in 0 until 10) {
            val item = BikePartItem(
                "Вилка $i",
                "шестигранник на $i",
                5.5,
                true
            )
            addBikePartItem(item)
        }
    }

    override fun addBikePartItem(bikePartItem: BikePartItem) {
        if (bikePartItem.id == BikePartItem.UNDEFINED_ID) {
            bikePartItem.id = autoIncrementId++
        }
        bikePartsList.add(bikePartItem)
        updateList()
    }

    override fun deleteBikePartItem(bikePartItem: BikePartItem) {
        bikePartsList.remove(bikePartItem)
        updateList()
    }

    override fun editBitePartItem(bikePartItem: BikePartItem) {
        val oldElement = getBikePartItem(bikePartItem.id)
        bikePartsList.remove(oldElement)
        addBikePartItem(bikePartItem)
    }

    override fun getBikePartItem(bikePartItemId: Int): BikePartItem {
        return bikePartsList.find {
            it.id == bikePartItemId
        } ?: throw RuntimeException("Element with id $bikePartItemId not found")
    }

    override fun getBikePartsList(): LiveData<List<BikePartItem>> {
        return bikePartsListLD
    }

    private fun updateList(){
        bikePartsListLD.value = bikePartsList.toList()
    }
}