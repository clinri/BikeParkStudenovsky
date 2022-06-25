package ru.netology.bikeparkstudenovsky.presentation

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.textfield.TextInputLayout
import ru.netology.bikeparkstudenovsky.R
import ru.netology.bikeparkstudenovsky.domain.BikePartItem

class BikePartItemActivity : AppCompatActivity() {

    private lateinit var viewModel: BikePartItemViewModel

    private lateinit var tilName: TextInputLayout
    private lateinit var tilTools: TextInputLayout
    private lateinit var tilValue: TextInputLayout
    private lateinit var etName: EditText
    private lateinit var etTools: EditText
    private lateinit var etValue: EditText
    private lateinit var buttonSave: Button

    private var screenMode = MODE_UNKNOWN
    private var bikePartItemId = BikePartItem.UNDEFINED_ID


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bike_part_item)
        parseIntent()
        viewModel = ViewModelProvider(this)[BikePartItemViewModel::class.java]
        initViews()

        val mode = intent.getStringExtra(EXTRA_SCREEN_MODE)
        Log.d("BikePartItemActivity", mode.toString())
        if (EXTRA_SCREEN_MODE == MODE_EDIT) {
            val id = intent.getStringExtra(EXTRA_BIKE_PART_ITEM_ID)
            Log.d("bike_part_item_id", id.toString())
        }
        addTextChangesListeners()
        launchRightMode()
        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.errorInputDouble.observe(this) {
            val massage = if (it) {
                getString(R.string.error_input_double)
            } else {
                null
            }
            tilValue.error = massage
        }
        viewModel.errorInputName.observe(this) {
            val massage = if (it) {
                getString(R.string.error_input_name)
            } else {
                null
            }
            tilName.error = massage
        }
        viewModel.errorInputTools.observe(this) {
            val massage = if (it) {
                getString(R.string.error_input_tools)
            } else {
                null
            }
            tilTools.error = massage
        }
        viewModel.canCloseScreen.observe(this) {
            finish()
        }
    }

    private fun addTextChangesListeners() {
        etName.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                viewModel.resetErrorInputName()
            }

            override fun afterTextChanged(p0: Editable?) {
            }
        })
        etTools.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                viewModel.resetErrorInputTools()
            }

            override fun afterTextChanged(p0: Editable?) {
            }
        })
        etValue.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                viewModel.resetErrorInputDouble()
            }

            override fun afterTextChanged(p0: Editable?) {
            }
        })
    }

    private fun launchRightMode() {
        when (screenMode) {
            MODE_EDIT -> launchEditMode()
            MODE_ADD -> launchAddMode()
        }
    }

    private fun launchEditMode() {
        viewModel.getBikePartItem(bikePartItemId)
        viewModel.bikePartItem.observe(this) {
            etName.setText(it.name)
            etTools.setText(it.tools)
            etValue.setText(it.value.toString())
        }
        buttonSave.setOnClickListener {
            viewModel.editBikePartItem(
                etName.text?.toString(),
                etTools.text?.toString(),
                etValue.text?.toString()
            )
        }
    }

    private fun launchAddMode() {
        buttonSave.setOnClickListener {
            viewModel.addBikePartItem(
                etName.text?.toString(),
                etTools.text?.toString(),
                etValue.text?.toString()
            )
        }
    }

    private fun parseIntent() {
        if (!intent.hasExtra(EXTRA_SCREEN_MODE)) {
            throw RuntimeException("param screen mode is absent")
        }
        val mode = intent.getStringExtra(EXTRA_SCREEN_MODE)
        if (mode != MODE_ADD && mode != MODE_EDIT) {
            throw RuntimeException("Unknown screen mode $mode")
        }
        screenMode = mode
        if (screenMode == MODE_EDIT) {
            if (!intent.hasExtra(EXTRA_BIKE_PART_ITEM_ID)) {
                throw RuntimeException("Param bike part item id is absent")
            }
            bikePartItemId = intent.getIntExtra(EXTRA_BIKE_PART_ITEM_ID, BikePartItem.UNDEFINED_ID)
        }
    }

    private fun initViews() {
        tilName = findViewById(R.id.til_name)
        tilTools = findViewById(R.id.til_tools)
        tilValue = findViewById(R.id.til_count)
        etName = findViewById(R.id.et_name)
        etTools = findViewById(R.id.et_tools)
        etValue = findViewById(R.id.et_count)
        buttonSave = findViewById(R.id.save_button)
    }

    companion object {
        private const val EXTRA_SCREEN_MODE = "extra_mode"
        private const val EXTRA_BIKE_PART_ITEM_ID = "extra_bike_part_item_id"
        private const val MODE_EDIT = "mode_edit"
        private const val MODE_ADD = "mode_add"
        private const val MODE_UNKNOWN = ""

        fun newIntentAddItem(context: Context): Intent {
            val intent = Intent(context, BikePartItemActivity::class.java)
            intent.putExtra(EXTRA_SCREEN_MODE, MODE_ADD)
            return intent
        }

        fun newIntentEditItem(context: Context, bikePartItemId: Int): Intent {
            val intent = Intent(context, BikePartItemActivity::class.java)
            intent.putExtra(EXTRA_SCREEN_MODE, MODE_EDIT)
            intent.putExtra(EXTRA_BIKE_PART_ITEM_ID, bikePartItemId)
            return intent
        }
    }
}