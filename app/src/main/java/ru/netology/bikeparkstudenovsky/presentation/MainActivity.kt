package ru.netology.bikeparkstudenovsky.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.widget.LinearLayout
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import ru.netology.bikeparkstudenovsky.R
import ru.netology.bikeparkstudenovsky.domain.BikePartItem

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel
    private lateinit var adapter: BikePartListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        //[] аналог get(MainViewModel::class.java)
        setupRecyclerView()
        viewModel.bikePartsList.observe(this) {
            adapter.bikePartList = it
        }
    }

    private fun setupRecyclerView() {
        val rvBikePartList = findViewById<RecyclerView>(R.id.rv_bike_part_list)
        with(rvBikePartList) {
            adapter = BikePartListAdapter()
            adapter = adapter
            recycledViewPool.setMaxRecycledViews(
                BikePartListAdapter.VIEW_TYPE_ENABLED,
                BikePartListAdapter.MAX_POOL_SIZE
            )
            recycledViewPool.setMaxRecycledViews(
                BikePartListAdapter.VIEW_TYPE_DISABLED,
                BikePartListAdapter.MAX_POOL_SIZE
            )
        }
    }
}