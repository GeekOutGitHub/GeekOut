package course.labs.fragmentslab

import android.util.Log
import androidx.lifecycle.*

class CounterViewModel : ViewModel(), DefaultLifecycleObserver {

    // This class defines the ViewModel which keeps track of the number of times onCreate(), onStart() and onResume() have been called.
    companion object {
        private const val TAG = "CounterViewModel"
    }

    // TODO :
    private var _create = MutableLiveData<Int>()
    internal val create: LiveData<Int> = _create
    private var _start = MutableLiveData<Int>()
    internal val start: LiveData<Int> = _start
    private var _resume = MutableLiveData<Int>()
    internal val resume: LiveData<Int> = _resume
    // Create variables to keep a track of the number of times onCreate(), onStart() and onResume() have been called.
    // To keep track of each count, define two variables as specified below.
    // Define a private variable of type MutableLiveData that can only be modified within the ViewModel class.
    // Define an internal/public variable of type LiveData that can be accessed externally by the UI/fragment but cannot be modified.
    // Use a backing property to specify the getter function for the internal/public variable
    // Refer to the link below for a more detailed explanation/example
    // https://developer.android.com/codelabs/basic-android-kotlin-training-viewmodel#4


    init {
        // TODO:
        // Set initial value of counts to zero
        _create.value = 0
        _start.value = 0
        _resume.value = 0

    }

    internal fun bindToActivityLifecycle(mainActivity: MainActivity) {
        // TODO :
        mainActivity.lifecycle.addObserver(this)
        // Add the current instance of CounterViewModel as a LifeCycleObserver to the MainActivity
        // Use the addObserver function

    }

    override fun onResume(owner: LifecycleOwner) {
        // TODO :
        _resume.value = _resume.value!! + 1
        // Update the appropriate count variable
        Log.i(TAG,"Entered onResume")

    }

    override fun onCreate(owner: LifecycleOwner) {
        // TODO :
        _create.value = _create.value!! + 1
        // Update the appropriate count variable
        Log.i(TAG,"Entered onCreate")

    }

    override fun onStart(owner: LifecycleOwner) {
        // TODO :
        _start.value = _start.value!! + 1
        // Update the appropriate count variable
        Log.i(TAG,"Entered onStart")

    }


}