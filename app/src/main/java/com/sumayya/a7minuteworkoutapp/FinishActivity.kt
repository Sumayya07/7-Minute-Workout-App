package com.sumayya.a7minuteworkoutapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.sumayya.a7minuteworkoutapp.databinding.ActivityFinishBinding

class FinishActivity : AppCompatActivity() {

    // Create a binding variable
    private var binding: ActivityFinishBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inflate the layout
        binding = ActivityFinishBinding.inflate(layoutInflater)

        // Bind the layout to this Activity
        setContentView(binding?.root)

        // Attach the layout to this activity

        setSupportActionBar(binding?.toolbarFinishActivity)
        if (supportActionBar != null){
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }
        binding?.toolbarFinishActivity?.setNavigationOnClickListener {
            onBackPressed()
        }


        // Adding a click event to the Finish Button.
        binding?.btnFinish?.setOnClickListener {
            finish()
        }
    }
}