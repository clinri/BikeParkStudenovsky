package ru.netology.bikeparkstudenovsky.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import ru.netology.bikeparkstudenovsky.R

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel
    private lateinit var bikePartListAdapter: BikePartListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        //[] аналог get(MainViewModel::class.java)
        setupRecyclerView()
        viewModel.bikePartsList.observe(this) {
            bikePartListAdapter.submitList(it)
        }
    }

    private fun setupRecyclerView() {
        val rvBikePartList = findViewById<RecyclerView>(R.id.rv_bike_part_list)
        bikePartListAdapter = BikePartListAdapter()
        with(rvBikePartList) {
            adapter = bikePartListAdapter
            recycledViewPool.setMaxRecycledViews(
                BikePartListAdapter.VIEW_TYPE_ENABLED,
                BikePartListAdapter.MAX_POOL_SIZE
            )
            recycledViewPool.setMaxRecycledViews(
                BikePartListAdapter.VIEW_TYPE_DISABLED,
                BikePartListAdapter.MAX_POOL_SIZE
            )
        }
        setupLongClickListener()
        setupClickListener()
        setupSwipeListener(rvBikePartList)
    }

    private fun setupSwipeListener(rvBikePartList: RecyclerView) {
        val callback = object : ItemTouchHelper.SimpleCallback(
            0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val item = bikePartListAdapter.currentList[viewHolder.adapterPosition]
                viewModel.deleteBikePartItem(item)
            }
        }
        val itemTouchHelper = ItemTouchHelper(callback)
        itemTouchHelper.attachToRecyclerView(rvBikePartList)
    }

    private fun setupClickListener() {
        bikePartListAdapter.onBikePartItemClickListener = {
            Log.d("onBikePartItemClickListener", "Clicked on item $it")
        }
    }

    private fun setupLongClickListener() {
        bikePartListAdapter.onBikePartItemLongClickListener = {
            viewModel.changeEnableState(it)
        }
    }
}