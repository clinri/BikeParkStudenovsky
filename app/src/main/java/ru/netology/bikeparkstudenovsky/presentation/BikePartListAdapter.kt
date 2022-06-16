package ru.netology.bikeparkstudenovsky.presentation

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import ru.netology.bikeparkstudenovsky.R
import ru.netology.bikeparkstudenovsky.domain.BikePartItem

class BikePartListAdapter :
    ListAdapter<BikePartItem, BikeBartItemViewHolder>(
        BikePartItemDiffCallback()

    ) {

    var count = 0

    var onBikePartItemLongClickListener: ((BikePartItem) -> Unit)? = null
    var onBikePartItemClickListener: ((BikePartItem) -> Unit)? = null


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BikeBartItemViewHolder {

        val layout = when (viewType) {
            VIEW_TYPE_ENABLED -> R.layout.item_bike_part_enabled
            VIEW_TYPE_DISABLED -> R.layout.item_bike_part_disabled
            else -> throw RuntimeException("Unknown view type: $viewType")
        }
        val view = LayoutInflater.from(parent.context).inflate(layout, parent, false)
        return BikeBartItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: BikeBartItemViewHolder, position: Int) {
        Log.d("BikePartListAdapter", "onBindViewHolder ${++count}")
        val bikePartItem = getItem(position)
        holder.tvName.text = bikePartItem.name
        holder.tvTools.text = bikePartItem.tools
        holder.tvValue.text = bikePartItem.value.toString()
        holder.itemView.setOnLongClickListener {
            onBikePartItemLongClickListener?.invoke(bikePartItem)
            true
        }
        holder.itemView.setOnClickListener {
            onBikePartItemClickListener?.invoke(bikePartItem)
            true
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (getItem(position).enabled) {
            VIEW_TYPE_ENABLED
        } else {
            VIEW_TYPE_DISABLED
        }
    }

    companion object {
        const val VIEW_TYPE_ENABLED = 100
        const val VIEW_TYPE_DISABLED = 101
        const val MAX_POOL_SIZE = 15
    }
}