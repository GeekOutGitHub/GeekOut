package course.labs.fragmentslab

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import course.labs.fragmentslab.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    // Create a private variable of type CounterViewModel to keep track of counts
    private val viewModel by viewModels<CounterViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Set content to the binding root view.
        setContentView(ActivityMainBinding.inflate(layoutInflater).root)

        // TODO:
        viewModel.bindToActivityLifecycle(this)
        // Initialize the CounterViewModel variable defined above
        // Bind CounterViewModel variable to the activity lifecycle
}
}