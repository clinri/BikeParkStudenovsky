package ru.netology.bikeparkstudenovsky.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.netology.bikeparkstudenovsky.data.BikePartsListRepositoryImpl
import ru.netology.bikeparkstudenovsky.domain.*
import java.lang.Exception

class BikePartItemViewModel : ViewModel() {
    private val repository = BikePartsListRepositoryImpl

    private val getBikePartItemUseCase = GetBikePartItemUseCase(repository)
    private val addBikePartItemUseCase = AddBikePartItemUseCase(repository)
    private val editBikePartItemUseCase = EditBikePartItemUseCase(repository)

    private val _errorInputName = MutableLiveData<Boolean>()
    val errorInputName: LiveData<Boolean>
        get() = _errorInputName

    private val _errorInputTools = MutableLiveData<Boolean>()
    val errorInputTools: LiveData<Boolean>
        get() = _errorInputTools

    private val _errorInputDouble = MutableLiveData<Boolean>()
    val errorInputDouble: LiveData<Boolean>
        get() = _errorInputDouble

    private val _bikePartItem = MutableLiveData<BikePartItem>()
    val bikePartItem: LiveData<BikePartItem>
        get() = _bikePartItem

    private val _canCloseScreen = MutableLiveData<Unit>()
    val canCloseScreen: LiveData<Unit>
        get() = _canCloseScreen

    fun getBikePartItem(bikePartItemId: Int) {
        val item = getBikePartItemUseCase.getBikePartItem(bikePartItemId)
        _bikePartItem.value = item
    }

    fun addBikePartItem(
        inputName: String?,
        inputTools: String?,
        inputValue: String?
    ) {
        val name = parseText(inputName)
        val tools = parseText(inputTools)
        val value = parseDouble(inputValue)
        val fieldsValid = validateInput(name, tools, value)
        if (fieldsValid) {
            val bikePartItem = BikePartItem(name, tools, value, true)
            addBikePartItemUseCase.addBikePartItem(bikePartItem)
        }
        finishWork()
    }

    fun editBikePartItem(
        inputName: String?,
        inputTools: String?,
        inputValue: String?
    ) {
        val name = parseText(inputName)
        val tools = parseText(inputTools)
        val value = parseDouble(inputValue)
        val fieldsValid = validateInput(name, tools, value)
        if (fieldsValid) {
            _bikePartItem.value?.let {
                val item = it.copy(name = name, tools = tools, value = value)
                editBikePartItemUseCase.editBitePartItem(item)
                finishWork()
            }
        }
    }

    private fun parseText(inputText: String?): String {
        return inputText?.trim() ?: ""
    }

    private fun parseDouble(inputText: String?): Double {
        return try {
            inputText?.trim()?.toDouble() ?: 0.0
        } catch (e: Exception) {
            0.0
        }
    }

    private fun validateInput(name: String, tools: String, value: Double): Boolean {
        var result = true
        if (name.isBlank()) {
            _errorInputName.value = true
            result = false
        }
        if (tools.isBlank()) {
            _errorInputTools.value = true
            result = false
        }
        if (value <= 0) {
            _errorInputDouble.value = true
            result = false
        }
        return result
    }

    fun resetErrorInputName() {
        _errorInputName.value = false
    }

    fun resetErrorInputTools() {
        _errorInputTools.value = false
    }

    fun resetErrorInputDouble() {
        _errorInputDouble.value = false
    }

    private fun finishWork() {
        _canCloseScreen.value = Unit
    }
}