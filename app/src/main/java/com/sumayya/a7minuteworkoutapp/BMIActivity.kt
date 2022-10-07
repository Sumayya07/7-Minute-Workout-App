package com.sumayya.a7minuteworkoutapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.sumayya.a7minuteworkoutapp.databinding.ActivityBmiBinding


// Creating a BMI calculator activity.
class BMIActivity : AppCompatActivity() {

    // Create binding for the activity
    private var binding: ActivityBmiBinding? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inflate the layout
        binding = ActivityBmiBinding.inflate(layoutInflater)
        // Connect the layout to this activity
        setContentView(binding?.root)

        // Setting up an action bar in BMI calculator activity using toolbar and adding a back arrow button along with click event.
        setSupportActionBar(binding?.toolbarBmiActivity)
        supportActionBar?.setDisplayHomeAsUpEnabled(true) // set back button
        supportActionBar?.title = "CALCULATE BMI" // Setting a title in the action bar.

        binding?.toolbarBmiActivity?.setNavigationOnClickListener {
            onBackPressed()

        }


    }
}