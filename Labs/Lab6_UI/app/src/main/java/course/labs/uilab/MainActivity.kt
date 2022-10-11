package course.labs.uilab

import android.graphics.Color
import android.os.Bundle
import android.widget.SeekBar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.graphics.toColor
import course.labs.uilab.databinding.ActivityMainBinding
import java.util.*

class MainActivity : AppCompatActivity() {

    // This lab is for understanding how to use and update UI elements of an Android App
    // The color in the black box and the color hex code should update based on the changes made to the 4 bars: alpha, red, green and blue

    private lateinit var binding: ActivityMainBinding
    // TODO :
    private var red: Int = 0
    private var green: Int = 0
    private var blue: Int = 0
    private var alpha: Int = 255
    // Create 4 Int variables to store the value of the alpha, red, green and blue progress bar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpUI()
    }

    private fun setUpUI() {
        // TODO :
        // 4 lines of code, assign progress bar value (not the bar itself) from the UI to the 4 Int variables created initially


        binding.alphaBar.setOnSeekBarChangeListener(
            object : SeekBar.OnSeekBarChangeListener {
                override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                    // TODO:
                    alpha = p1
                    updateColorDisplay()
                    // assign the changed alpha value to the alpha variable
                    // call the updateColorDisplay function
                }
                //Not Implemented
                override fun onStartTrackingTouch(p0: SeekBar?) {}
                override fun onStopTrackingTouch(p0: SeekBar?) {}
            })

        binding.redBar.setOnSeekBarChangeListener(
            object : SeekBar.OnSeekBarChangeListener {
                override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                    // TODO:
                    red = p1
                    updateColorDisplay()
                    // assign the changed red value to the red variable
                    // call the updateColorDisplay function
                }
                //Not Implemented
                override fun onStartTrackingTouch(p0: SeekBar?) {}
                override fun onStopTrackingTouch(p0: SeekBar?) {}
            })

        binding.greenBar.setOnSeekBarChangeListener(
            object : SeekBar.OnSeekBarChangeListener {
                override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                    // TODO:
                    green = p1
                    updateColorDisplay()
                    // assign the changed green value to the green variable
                    // call the updateColorDisplay function
                }
                //Not Implemented
                override fun onStartTrackingTouch(p0: SeekBar?) {}
                override fun onStopTrackingTouch(p0: SeekBar?) {}
            })

        binding.blueBar.setOnSeekBarChangeListener(
            object : SeekBar.OnSeekBarChangeListener {
                override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                    // TODO:
                    blue = p1
                    updateColorDisplay()
                    // assign the changed blue value to the blue variable
                    // call the updateColorDisplay function
                }
                //Not Implemented
                override fun onStartTrackingTouch(p0: SeekBar?) {}
                override fun onStopTrackingTouch(p0: SeekBar?) {}
            })

        // TODO:
        updateColorDisplay()
        // call the updateColorDisplay function
    }

    private fun updateColorDisplay() {

        // TODO:
        binding.currColor.text = getString(R.string.current_color_string,toHex(alpha),toHex(red),toHex(green),toHex(blue))

        // Set the string value in the UI using the getString function
        // Use the "current_color_string" from the string resources to set the text format in the getString function
        // Use the toHex function to convert alpha, red, green and blue values to hex

        // TODO:
        binding.colorView.setBackgroundColor(Color.argb(alpha, red, green, blue))
        // Set the color in the square box using the Color.argb() function
    }

    // Convenience function to construct ARGB components
    private fun toHex(value: Int): String {
        val tmp = Integer.toHexString(value)
        return if (tmp.length <= 1) ("0$tmp").uppercase(Locale.ROOT) else tmp.uppercase(Locale.ROOT)
    }
}