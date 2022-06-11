package ru.netology.bikeparkstudenovsky.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.widget.LinearLayout
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import ru.netology.bikeparkstudenovsky.R
import ru.netology.bikeparkstudenovsky.domain.BikePartItem

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel
    private lateinit var llBikePartList: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        llBikePartList = findViewById(R.id.ll_bike_part_list)
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        //[] аналог get(MainViewModel::class.java)
        viewModel.bikePartsList.observe(this) {
            showList(it)
        }
    }

    private fun showList(list: List<BikePartItem>) {
        llBikePartList.removeAllViews()
        for (bikePartItem in list){
            val layoutId = if (bikePartItem.enabled){
                R.layout.itrm_bike_part_enabled
            } else {
                R.layout.item_bike_part_disabled
            }
            val view = LayoutInflater.from(this).inflate(layoutId, llBikePartList, false)
            view.setOnLongClickListener{
                viewModel.changeEnableState(bikePartItem)
                true
            }
            val tvName = view.findViewById<TextView>(R.id.tv_name)
            val tvTools = view.findViewById<TextView>(R.id.tv_tools)
            val tvValue = view.findViewById<TextView>(R.id.tv_value)
            tvName.text = bikePartItem.name
            tvTools.text = bikePartItem.tools
            tvValue.text = bikePartItem.value.toString()
            llBikePartList.addView(view)
        }

    }
}