package com.sumayya.a7minuteworkoutapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.sumayya.a7minuteworkoutapp.databinding.ActivityHistoryBinding
import kotlinx.coroutines.launch

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

        // Get the dao through the database in the application class, then call getAllCompletedDates method and pass in the dao
        val dao = (application as WorkOutApp).db.historyDao()
        getAllCompletedDates(dao)
    }


    // Created a function to get the list of completed dates from the History Table.)
    // Function is used to get the list exercise completed dates.
    private fun getAllCompletedDates(historyDao: HistoryDao) {

        lifecycleScope.launch {
            historyDao.fetchALlDates().collect { allCompletedDatesList ->

                // Here the dates which were printed in log.
                //  We will pass that list to the adapter class which we have created and bind it to the recycler view.)
                if (allCompletedDatesList.isNotEmpty()) {

                    // Here if the List size is greater then 0 we will display the item in the recycler view or else we will show the text view that no data is available.
                    binding?.tvHistory?.visibility = View.VISIBLE
                    binding?.rvHistory?.visibility = View.VISIBLE
                    binding?.tvNoDataAvailable?.visibility = View.GONE

                    // Creates a vertical Layout Manager
                    binding?.rvHistory?.layoutManager = LinearLayoutManager(this@HistoryActivity)

                    // History adapter is initialized and the list is passed in the param.
                    val dates = ArrayList<String>()
                    for (date in allCompletedDatesList){
                        dates.add(date.date)
                    }
                    val historyAdapter = HistoryAdapter(ArrayList(dates))

                    // Access the RecyclerView Adapter and load the data into it
                    binding?.rvHistory?.adapter = historyAdapter
                } else {
                    binding?.tvHistory?.visibility = View.GONE
                    binding?.rvHistory?.visibility = View.GONE
                    binding?.tvNoDataAvailable?.visibility = View.VISIBLE
                }
            }
        }
    }


    override fun onDestroy() {
        super.onDestroy()
        // reset the binding to null to avoid memory leak
        binding = null
    }
}