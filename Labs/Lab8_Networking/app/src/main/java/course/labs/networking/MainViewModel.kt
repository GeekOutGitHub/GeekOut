package course.labs.networking

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.ktor.client.*
import io.ktor.client.request.*
import kotlinx.coroutines.*

class MainViewModel : ViewModel() {

    // Defining a pair of LiveData and MutableLiveData variables of type Status
    // The Status variable will be updated to display the downloaded bitmap, the status of the network request and
    // error messages if any
    private val _statusLiveData = MutableLiveData<Status>()
    val statusLiveData: LiveData<Status>
        get() = _statusLiveData


    /** Keep track of coroutine so that duplicate requests are ignored. */
    private var job: Job? = null

    /**
     * Defining a sealed class to store the status of the network request,
     * the downloaded bitmap and the error message, if any
     */
    sealed class Status {
        data class Progress(val progress: Int) : Status()
        data class Result(val image: Bitmap) : Status()
        data class Error(val errorResId: Int, val e: Exception) : Status()
    }

    /**
     * sendNetworkRequest() is a non-blocking function called by the UI (Fragment) when the
     * user clicks the send button. This function starts a coroutine
     * that sends a network request and then posts downloaded bitmap
     * on the LiveData feed that can be observed by the calling
     * Fragment.
     */
    fun sendNetworkRequest() {
        // TODO:
        if (job?.isActive == true) {
            return
        }
        // Ignore button click if a request is still active.

        // TODO:
        _statusLiveData.value = Status.Progress(R.string.get_request_progress)
        // Update the Progress field of the Status LiveData feed and say that the GET request is being performed

        // Launch a new coroutine to run network request in the background.
        job = viewModelScope.launch {
            try {
                // TODO:
                val rawBitmap = makeNetworkCall(URL)
                // Run the suspending network request to download the bitmap and store it in a variable

                // TODO:
                _statusLiveData.postValue(Status.Result(rawBitmap))
                // Post the downloaded bitmap to the Result field of the Status LiveData Feed

            } catch (e: Exception) {
                // TODO:
                _statusLiveData.postValue(Status.Error(R.string.send_request_error, e))
                // Something went wrong ... post an error message to the Error field of the Status LiveData feed.

            }
        }
    }

    /**
     * makeNetworkCall() is a suspending helper function that performs the network request
     * specified by the passed [url] and returns a Bitmap.
     */
    private suspend fun makeNetworkCall(url: String): Bitmap =
        withContext(Dispatchers.IO) {

            // For testing purposes, simulate network delay
            delay(2000)

            // TODO:
            val map = BitmapFactory.decodeStream(HttpClient().get(url))
            Bitmap.createScaledBitmap(map, SIZE, SIZE, true)
            // Construct a new Ktor HttpClient to perform the GET
            // request and then return the resulting Bitmap
            // Use the decodeStream function from BitmapFactory to decode the HttpClient output into a Bitmap
            // Resize the Bitmap to dimension (SIZE, SIZE) using the createScaledBitmap function

            // Replace the line below with the code to download the bitmap
            // The line below has only been included so that the initial app compiles without errors
            // Bitmap.createBitmap(SIZE, SIZE, Bitmap.Config.RGB_565)
        }

    /**
     * Constants
     * Use these constants in the makeNetworkCall() function
     * Download a square image of dimension (SIZE, SIZE) specified in URL below
     */
    companion object {
        //private const val URL =
           // "https://image-charts.com/chart?chs=150x150&cht=qr&chl=https://www.cs.umd.edu/class/fall2022/cmsc436/&choe=UTF-8"
        private const val URL = "https://api.qrserver.com/v1/create-qr-code/?size=150x150&data=https://www.cs.umd.edu/class/fall2022/cmsc436/&choe=UTF-8"
        private const val SIZE = 450
    }
}