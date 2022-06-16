package ru.netology.bikeparkstudenovsky.presentation

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ru.netology.bikeparkstudenovsky.R

class BikeBartItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val tvName = view.findViewById<TextView>(R.id.tv_name)
    val tvTools = view.findViewById<TextView>(R.id.tv_tools)
    val tvValue = view.findViewById<TextView>(R.id.tv_value)
}
