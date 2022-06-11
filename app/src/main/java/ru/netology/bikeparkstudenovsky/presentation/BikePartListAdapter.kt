package ru.netology.bikeparkstudenovsky.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import ru.netology.bikeparkstudenovsky.R
import ru.netology.bikeparkstudenovsky.domain.BikePartItem

class BikePartListAdapter : RecyclerView.Adapter<BikePartListAdapter.BikeBartItemViewHolder>() {

    var bikePartList = listOf<BikePartItem>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BikeBartItemViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.itrm_bike_part_enabled, parent, false)
        return BikeBartItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: BikeBartItemViewHolder, position: Int) {
        val bikePartItem = bikePartList[position]
        val status = if (bikePartItem.enabled){
            "Active"
        } else {
            "Not active"
        }
        holder.tvName.text = "${bikePartItem.name} $status"
        holder.tvTools.text = bikePartItem.tools
        holder.tvValue.text = bikePartItem.value.toString()
        holder.itemView.setOnLongClickListener {
            true
        }
        if (bikePartItem.enabled){
            holder.tvName.setTextColor(ContextCompat.getColor(holder.itemView.context,android.R.color.holo_red_light))
        } else {
            holder.tvName.setTextColor(ContextCompat.getColor(holder.itemView.context,android.R.color.white))
        }
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    override fun getItemCount(): Int {
        return bikePartList.size
    }

    class BikeBartItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvName = view.findViewById<TextView>(R.id.tv_name)
        val tvTools = view.findViewById<TextView>(R.id.tv_tools)
        val tvValue = view.findViewById<TextView>(R.id.tv_value)
    }
}