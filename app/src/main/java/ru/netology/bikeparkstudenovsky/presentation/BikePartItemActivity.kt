package ru.netology.bikeparkstudenovsky.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ru.netology.bikeparkstudenovsky.R

class BikePartItemActivity : AppCompatActivity() {

    private lateinit var viewModel: BikePartItemViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bike_part_item)

    }
}