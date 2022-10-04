package com.sumayya.a7minuteworkoutapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.sumayya.a7minuteworkoutapp.databinding.ActivityExerciseBinding

class ExerciseActivity : AppCompatActivity() {
    private var binding: ActivityExerciseBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityExerciseBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        // To use a toolbar inside our exercise activity we use this method
        setSupportActionBar(binding?.toolbarExercise)

        // Here we are adding the back button in the toolbar.
        if (supportActionBar != null){
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }
        binding?.toolbarExercise?.setNavigationOnClickListener{
            onBackPressed() // This method will take us to the previous screen.
        }
    }
}