package course.labs.activitylab

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import course.labs.activitylab.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    // This lab is for understanding the lifecycle of Android Activity

    // Create counter variables for the number of times onCreate(), onRestart(), onStart() and
    // onResume() have been called
    // Increment these variables' values when their corresponding lifecycle methods get called
    // Update display of counts as appropriate

    // [Note]
    // Store and restore counter values to preserve values across reconfiguration and
    // starting/returning from SecondActivity

    private lateinit var binding: ActivityMainBinding

    // TODO:
    // Create four counter variables for onCreate(), onRestart(), onStart() and onResume()
    // Only need 4 lines of code

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i(TAG, "onCreate() called")

        // Pass the root view to setContentView() to make it the active view on the screen
        // More details about view binding available at "https://developer.android.com/topic/libraries/view-binding"
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // This is a button starting the second activity
        val secondActivityLaunchBtn = binding.fab
        secondActivityLaunchBtn.setOnClickListener {
            // TODO :
            // Launch the SecondActivity when this button is clicked
            // [Hint] Use startActivity() method with intent to start the SecondActivity
        }

        // If there's any saved previous state, we need to restore
        // Complete the function - restoreCounts
        savedInstanceState?.apply { restoreCounts(savedInstanceState) }

        // TODO :
        // Update the appropriate count variable
    }

    private fun displayCounts() {
        // Call this function when the screen needs to be updated
        // TODO :
        // Complete the function - update TextViews to show the current
        // values of the variables of the counters
        // [Hint] Access TextViews using "binding" object like we did with "secondActivityLaunchBtn"

        // Only need 4 lines of code, one TextView for each counter variable
    }

    private fun restoreCounts (savedInstanceState:Bundle) {
        // TODO :
        // Only need 4 lines of code, restore four count variables using savedInstanceState
        // [Hint] You can get saved values from from savedInstanceState using "keys" defined
        // in the companion object.
    }

    public override fun onSaveInstanceState(savedInstanceState: Bundle) {
        super.onSaveInstanceState(savedInstanceState)
        // TODO :
        // Save counter information with a collection of key-value pairs
        // As keys, use const values defined in the companion object
        // Only 4 lines need for each counter variable
    }

    override fun onStart() {
        super.onStart()
        Log.i(TAG, "onStart() called")
        // TODO :
        // Update the appropriate count variable
    }

    override fun onResume() {
        super.onResume()
        Log.i(TAG, "onResume() called")
        // TODO :
        // 1. Update the appropriate count variable
        // 2. Update the user interface
    }

    override fun onRestart() {
        super.onRestart()
        Log.i(TAG, "onRestart() called")
        // TODO :
        // Update the appropriate count variable
    }

    companion object {
        // Use these values as map keys for storing/retrieving respective counter values
        private const val RESTART_KEY = "restart"
        private const val RESUME_KEY = "resume"
        private const val START_KEY = "start"
        private const val CREATE_KEY = "create"

        private const val TAG = "Lab-Activity"
    }
}