package course.labs.broadcastreceiverslab

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

class PowerConnectionStatusReceiver(
    private val viewModel: PowerStatusViewModel
) : BroadcastReceiver() {
    // This class is for receiving messages of the device's power connection status from the Android system
    override fun onReceive(context: Context?, intent: Intent?) {
        // TODO :
        viewModel.isPowerConnected.value = intent?.action == Intent.ACTION_POWER_CONNECTED
        // Update the viewModel's connection status value according using Intent action

    }
}