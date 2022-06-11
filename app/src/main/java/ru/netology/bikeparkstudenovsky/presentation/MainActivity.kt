package ru.netology.bikeparkstudenovsky.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import ru.netology.bikeparkstudenovsky.R

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel
    private lateinit var bikePartAdapter: BikePartListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        //[] аналог get(MainViewModel::class.java)
        setupRecyclerView()
        viewModel.bikePartsList.observe(this) {
            bikePartAdapter.bikePartList = it
        }
    }

    private fun setupRecyclerView() {
        val rvBikePartList = findViewById<RecyclerView>(R.id.rv_bike_part_list)
        bikePartAdapter = BikePartListAdapter()
        with(rvBikePartList) {
            adapter = bikePartAdapter
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