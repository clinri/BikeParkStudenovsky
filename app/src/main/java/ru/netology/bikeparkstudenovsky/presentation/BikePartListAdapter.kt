package ru.netology.bikeparkstudenovsky.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ru.netology.bikeparkstudenovsky.R
import ru.netology.bikeparkstudenovsky.domain.BikePartItem

class BikePartListAdapter : RecyclerView.Adapter<BikePartListAdapter.BikeBartItemViewHolder>() {

    val list = listOf<BikePartItem>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BikeBartItemViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.itrm_bike_part_enabled, parent, false)
        return BikeBartItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: BikeBartItemViewHolder, position: Int) {
        val bikePartItem = list[position]
        holder.tvName.text = bikePartItem.name
        holder.tvTools.text = bikePartItem.tools
        holder.tvValue.text = bikePartItem.value.toString()
        holder.itemView.setOnLongClickListener {
            true
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    class BikeBartItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvName = view.findViewById<TextView>(R.id.tv_name)
        val tvTools = view.findViewById<TextView>(R.id.tv_tools)
        val tvValue = view.findViewById<TextView>(R.id.tv_value)
    }
}