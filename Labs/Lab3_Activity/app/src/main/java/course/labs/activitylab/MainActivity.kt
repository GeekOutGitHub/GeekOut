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
    private var countOnCreate = 0
    private var countOnRestart = 0
    private var countOnStart = 0
    private var countOnResume = 0
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
            val intent = Intent(this@MainActivity,SecondActivity::class.java)
            startActivity(intent)
            // Launch the SecondActivity when this button is clicked
            // [Hint] Use startActivity() method with intent to start the SecondActivity
        }

        // If there's any saved previous state, we need to restore
        // Complete the function - restoreCounts
        savedInstanceState?.apply { restoreCounts(savedInstanceState) }

        // TODO :
        countOnCreate++
        // Update the appropriate count variable
    }

    private fun displayCounts() {
        // Call this function when the screen needs to be updated
        // TODO :
        binding.onCreate.setText("onCreate() calls: $countOnCreate")
        binding.onStart.setText("onStart() calls: $countOnStart")
        binding.onResume.setText("onResume() calls: $countOnResume")
        binding.onRestart.setText("onRestart() calls: $countOnRestart")
        // Complete the function - update TextViews to show the current
        // values of the variables of the counters
        // [Hint] Access TextViews using "binding" object like we did with "secondActivityLaunchBtn"

        // Only need 4 lines of code, one TextView for each counter variable
        binding.onCreate.setText("hello")
    }

    private fun restoreCounts (savedInstanceState:Bundle) {
        // TODO :
        countOnCreate = savedInstanceState.getInt(CREATE_KEY)
        countOnStart = savedInstanceState.getInt(START_KEY)
        countOnResume = savedInstanceState.getInt(RESUME_KEY)
        countOnRestart = savedInstanceState.getInt(RESTART_KEY)
        // Only need 4 lines of code, restore four count variables using savedInstanceState
        // [Hint] You can get saved values from from savedInstanceState using "keys" defined
        // in the companion object.
    }

    public override fun onSaveInstanceState(savedInstanceState: Bundle) {
        super.onSaveInstanceState(savedInstanceState)
        // TODO :
        savedInstanceState.putInt(CREATE_KEY,countOnCreate)
        savedInstanceState.putInt(START_KEY,countOnStart)
        savedInstanceState.putInt(RESUME_KEY,countOnResume)
        savedInstanceState.putInt(RESTART_KEY,countOnRestart)
        // Save counter information with a collection of key-value pairs
        // As keys, use const values defined in the companion object
        // Only 4 lines need for each counter variable
    }

    override fun onStart() {
        super.onStart()
        Log.i(TAG, "onStart() called")
        // TODO :
        countOnStart++
        // Update the appropriate count variable
    }

    override fun onResume() {
        super.onResume()
        Log.i(TAG, "onResume() called")
        // TODO :
        countOnResume++
        displayCounts()
        // 1. Update the appropriate count variable
        // 2. Update the user interface
    }

    override fun onRestart() {
        super.onRestart()
        Log.i(TAG, "onRestart() called")
        // TODO :
        countOnRestart++
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