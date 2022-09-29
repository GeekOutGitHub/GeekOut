package course.labs.fragmentslab

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import course.labs.fragmentslab.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    // Create a private variable of type CounterViewModel to keep track of counts
    private lateinit var viewModel: CounterViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Set content to the binding root view.
        setContentView(ActivityMainBinding.inflate(layoutInflater).root)

        // TODO:
        // Initialize the CounterViewModel variable defined above
        // Bind CounterViewModel variable to the activity lifecycle
}
}