package com.sumayya.a7minuteworkoutapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.sumayya.a7minuteworkoutapp.databinding.ActivityHistoryBinding

// Adding the History Screen Activity
class HistoryActivity : AppCompatActivity() {

    // create a binding for the layout
    private var binding: ActivityHistoryBinding? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // inflate the layout
        binding = ActivityHistoryBinding.inflate(layoutInflater)

        //bind the layout to this activity
        setContentView(binding?.root)


        // Setting up the action bar in the History Screen Activity and
        // adding a back arrow button and click event for it.)
        setSupportActionBar(binding?.toolbarHistoryActivity)
        val actionbar = supportActionBar//actionbar
        if (actionbar != null) {
            actionbar.setDisplayHomeAsUpEnabled(true) //set back button
            actionbar.title = "HISTORY" // Setting a title in the action bar.
        }

        binding?.toolbarHistoryActivity?.setNavigationOnClickListener {
            onBackPressed()
        }
    }


    override fun onDestroy() {
        super.onDestroy()
        // reset the binding to null to avoid memory leak
        binding = null
    }
}