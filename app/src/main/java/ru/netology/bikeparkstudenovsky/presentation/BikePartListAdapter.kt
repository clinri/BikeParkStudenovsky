package ru.netology.bikeparkstudenovsky.presentation

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import ru.netology.bikeparkstudenovsky.R
import ru.netology.bikeparkstudenovsky.domain.BikePartItem
import java.lang.RuntimeException

class BikePartListAdapter : RecyclerView.Adapter<BikePartListAdapter.BikeBartItemViewHolder>() {

    var count = 0
    var bikePartList = listOf<BikePartItem>()
        set(value) {
            val callback = BikePartListDiffCallback(bikePartList,value)
            val diffResult = DiffUtil.calculateDiff(callback)
            diffResult.dispatchUpdatesTo(this)
            field = value
        }

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
        val bikePartItem = bikePartList[position]
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
        return if (bikePartList[position].enabled) {
            VIEW_TYPE_ENABLED
        } else {
            VIEW_TYPE_DISABLED
        }
    }

    override fun getItemCount(): Int {
        return bikePartList.size
    }

    class BikeBartItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvName = view.findViewById<TextView>(R.id.tv_name)
        val tvTools = view.findViewById<TextView>(R.id.tv_tools)
        val tvValue = view.findViewById<TextView>(R.id.tv_value)
    }

    companion object {
        const val VIEW_TYPE_ENABLED = 100
        const val VIEW_TYPE_DISABLED = 101
        const val MAX_POOL_SIZE = 15
    }

    interface OnBikePartItemLongClickListener{
        fun onBikePartItemLongClick(bikePartItem: BikePartItem)
    }
}