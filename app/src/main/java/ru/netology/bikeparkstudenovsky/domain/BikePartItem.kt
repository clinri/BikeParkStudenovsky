package ru.netology.bikeparkstudenovsky.domain

data class BikePartItem(
    val name: String,
    val tools: String,
    val value: Double,
    val enabled: Boolean,
    var id: Int = UNDEFINED_ID
){
    companion object{
        const val UNDEFINED_ID = -1
    }
}
