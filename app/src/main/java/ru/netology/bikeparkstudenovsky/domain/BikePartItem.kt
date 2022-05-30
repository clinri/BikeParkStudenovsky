package ru.netology.bikeparkstudenovsky.domain

data class BikePartItem(
    val id: Int,
    val name: String,
    val tools: String,
    val value: Double,
    val enabled: Boolean
)
