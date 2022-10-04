package course.labs.fragmentslab

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import course.labs.fragmentslab.databinding.FirstFragmentBinding

class FirstFragment : Fragment() {

    /** Binding to XML layout */
    private lateinit var binding: FirstFragmentBinding
    // Create a variable of type CounterViewModel to keep track of counts
    private val viewModel by activityViewModels<CounterViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Use the provided binding object to inflate the layout.
        binding = FirstFragmentBinding.inflate(inflater, container, false)

        // Update ActionBar label to distinguish which Fragment is displayed
        (requireActivity() as AppCompatActivity).supportActionBar?.title = this.javaClass.simpleName

        // Set onClickListener to navigate to the second fragmant from the first
        binding.fab.setOnClickListener {
            findNavController().navigate(FirstFragmentDirections.actionFirstFragmentToSecondFragment()
            )
        }
        // Return the root view.
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // TODO:
        //already initialized
        // Initialize CounterViewModel instance

        // TODO:
        binding.onCreate.text = getString(R.string.oncreate_calls_string, viewModel.create.value)
        binding.onStart.text = getString(R.string.onstart_calls_string, viewModel.start.value)
        binding.onResume.text = getString(R.string.onresume_calls_string, viewModel.resume.value)
        // Use binding to display initial counts

        // The function below updates the counts over time
        beginObservingCounter()
    }

    private fun beginObservingCounter() {

        // TODO:
        viewModel.create.observe(viewLifecycleOwner) {
            binding.onCreate.text = getString(R.string.oncreate_calls_string, viewModel.create.value)
        }
        viewModel.start.observe(viewLifecycleOwner) {
            binding.onStart.text = getString(R.string.onstart_calls_string, viewModel.start.value)
        }
        viewModel.resume.observe(viewLifecycleOwner) {
            binding.onResume.text = getString(R.string.onresume_calls_string, viewModel.resume.value)
        }
        // Register observers for each of the count variables
        // In the body of the observe function, update the text to be displayed by using the binding

    }
}