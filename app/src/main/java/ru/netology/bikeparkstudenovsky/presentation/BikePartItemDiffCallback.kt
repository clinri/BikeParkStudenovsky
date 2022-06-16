package ru.netology.bikeparkstudenovsky.presentation

import androidx.recyclerview.widget.DiffUtil
import ru.netology.bikeparkstudenovsky.domain.BikePartItem

class BikePartItemDiffCallback: DiffUtil.ItemCallback<BikePartItem>() {
    override fun areItemsTheSame(oldItem: BikePartItem, newItem: BikePartItem): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: BikePartItem, newItem: BikePartItem): Boolean {
        return oldItem == newItem
    }
}