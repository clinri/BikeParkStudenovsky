package ru.netology.bikeparkstudenovsky.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.netology.bikeparkstudenovsky.data.BikePartsListRepositoryImpl
import ru.netology.bikeparkstudenovsky.domain.*

class MainViewModel : ViewModel() {
    private val repository = BikePartsListRepositoryImpl

    private val getBikePartsListUseCase = GetBikePartsListUseCase(repository)
    private val deleteBikePartItemUseCase = DeleteBikePartItemUseCase(repository)
    private val editBikePartItemUseCase = EditBikePartItemUseCase(repository)

    val bikePartsList = getBikePartsListUseCase.getBikePartsList()

    fun deleteBikePartItem(bikePartItem: BikePartItem) {
        deleteBikePartItemUseCase.deleteBikePartItem(bikePartItem)
    }

    fun changeEnableState(bikePartItem: BikePartItem){
        val newItem = bikePartItem.copy(enabled = !bikePartItem.enabled)
        editBikePartItemUseCase.editBitePartItem(newItem)
    }
}