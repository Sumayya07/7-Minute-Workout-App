package com.sumayya.a7minuteworkoutapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.Toast
import com.sumayya.a7minuteworkoutapp.databinding.ActivityExerciseBinding

class ExerciseActivity : AppCompatActivity() {

    private var binding: ActivityExerciseBinding? = null

    // Timer for how much time you want to rest
    private var restTimer: CountDownTimer? = null
    // Variable for how far we have come
    private var restProgress = 0

    private var exerciseTimer: CountDownTimer? = null
    private var exerciseProgress = 0


    private var exerciseList: ArrayList<ExerciseModel>? = null
    private var currentExercisePosition = -1 // Current Position of Exercise.

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityExerciseBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        // To use a toolbar inside our exercise activity we use this method
        setSupportActionBar(binding?.toolbarExercise)

        // Here we are adding the back button in the toolbar.
        if (supportActionBar != null) {
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }
        binding?.toolbarExercise?.setNavigationOnClickListener {
            onBackPressed() // This method will take us to the previous screen.
        }

        // Initializing and Assigning a default exercise list to our list variable.
        exerciseList = Constants.defaultExerciseList()

        setupRestView()

    }


        private fun setupRestView(){
            binding?.flRestView?.visibility = View.VISIBLE
            binding?.tvTitle?.visibility = View.VISIBLE
            binding?.tvExerciseName?.visibility = View.INVISIBLE
            binding?.flExerciseView?.visibility = View.INVISIBLE
            binding?.ivImage?.visibility = View.INVISIBLE
            binding?.tvUpcomingLabel?.visibility = View.VISIBLE
            binding?.tvUpcomingExerciseName?.visibility = View.VISIBLE

            // Here firstly we will check if the timer is running the and it is not null then cancel the running timer and start the new one.
            // And set the progress to initial which is 0.
            if (restTimer != null){
                restTimer?.cancel()
                restProgress = 0
            }


            // Setting the upcoming exercise name in the UI element.
            // Here we have set the upcoming exercise name to the text view
            // Here as the current position is -1 by default so to selected from the list it should be 0 so we have increased it by +1.
            binding?.tvUpcomingExerciseName?.text = exerciseList!![currentExercisePosition + 1].getName()
            // This function is used to set the progress details.
            setRestProgressBar()
        }

    private fun setupExerciseView(){
        // Here we are hiding the progress bar so that we can show exercise progress bar.
        // Here according to the view make it visible as this is Exercise View so exercise view is visible and rest view is not.
        binding?.flRestView?.visibility = View.INVISIBLE
        binding?.tvTitle?.visibility = View.INVISIBLE
        binding?.tvExerciseName?.visibility = View.VISIBLE
        binding?.flExerciseView?.visibility = View.VISIBLE
        binding?.ivImage?.visibility = View.VISIBLE
        binding?.tvUpcomingLabel?.visibility = View.INVISIBLE
        binding?.tvUpcomingExerciseName?.visibility = View.INVISIBLE


        // Here we are making sure that we reset the exercise timer each time that we want to make it visible and set it up.
        if (exerciseTimer != null){
            exerciseTimer?.cancel()
            exerciseProgress = 0
        }

        // Setting up the current exercise name and image to view to the UI element.
        // Here current exercise name and image is set to exercise view.
        binding?.ivImage?.setImageResource(exerciseList!![currentExercisePosition].getImage())
        binding?.tvExerciseName?.text = exerciseList!![currentExercisePosition].getName()

        setExerciseProgressBar()
    }

        // This method will starts a timer
        private fun setRestProgressBar(){
            binding?.progressBar?.progress = restProgress

            restTimer = object: CountDownTimer(10000, 1000){ // After every 1000 milliseconds, this onTick method is called.
                override fun onTick(p0: Long) {
                    // Here we are increasing the restProgress by one and displaying the timer accordingly.
                    restProgress++
                    binding?.progressBar?.progress = 10 - restProgress
                    binding?.tvTimer?.text = (10 - restProgress).toString() // As we cannot assign number to our text, that is why we are converting it to a string.
                }
                // This method is for once the timer is done.
                override fun onFinish() {
                    // Increasing the current position of the exercise after rest view
                    currentExercisePosition++
                    setupExerciseView()
                }
            }.start() // It is really important to add the start at the end of the CountDownTimer object.
        }

    // This method is for exercise timer
    private fun setExerciseProgressBar(){
        binding?.progressBarExercise?.progress = exerciseProgress

        exerciseTimer = object: CountDownTimer(30000, 1000){ // After every 1000 milliseconds, this onTick method is called.
            override fun onTick(p0: Long) {
                exerciseProgress++
                binding?.progressBarExercise?.progress = 30 - exerciseProgress
                binding?.tvTimerExercise?.text = (30 - exerciseProgress).toString() // As we cannot assign number to our text, that is why we are converting it to a string.
            }
            // This method is for once the timer is done.
            override fun onFinish() {
                // Updating the view after completing the 30 seconds exercise.
                if (currentExercisePosition < exerciseList?.size!! - 1){
                    setupRestView()
                }else{
                Toast.makeText(this@ExerciseActivity, "Congratulations! You have completed the 7 minutes workout.", Toast.LENGTH_LONG).show()
            }
            }
        }.start()
    }

    override fun onDestroy() {
        super.onDestroy()
        if (restTimer != null){
            restTimer?.cancel()
            restProgress = 0
        }

        if (exerciseTimer != null){
            exerciseTimer?.cancel()
            exerciseProgress = 0
        }

        binding = null
    }



    }
