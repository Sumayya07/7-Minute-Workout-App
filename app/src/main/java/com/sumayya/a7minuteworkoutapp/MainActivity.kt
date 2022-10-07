package com.sumayya.a7minuteworkoutapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.renderscript.ScriptGroup.Binding
import android.widget.FrameLayout
import android.widget.Toast
import com.sumayya.a7minuteworkoutapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private var binding: ActivityMainBinding? = null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        binding?.flStart?.setOnClickListener {
            // Intent are used to move from one activity to another. Here we are moving from MainActivity to ExerciseActivity.
            val intent = Intent(this, ExerciseActivity::class.java)
            startActivity(intent)
        }

        // Adding a click event to the BMI calculator button and navigating it to the BMI calculator feature.
        binding?.flBMI?.setOnClickListener {
            // Launching the BMI Activity
            val intent = Intent(this, BMIActivity::class.java)
            startActivity(intent)
        }


    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}