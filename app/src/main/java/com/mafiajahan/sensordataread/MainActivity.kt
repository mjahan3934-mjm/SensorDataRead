package com.mafiajahan.sensordataread

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.mafiajahan.sensordataread.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), SensorEventListener {

    private lateinit var binding: ActivityMainBinding
    private lateinit var sensorManager: SensorManager
    private var lightSensor: Sensor? = null

    val TAG = "SensorApp"

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager

        lightSensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT)

        if (lightSensor == null) {
            Log.e(TAG, "Light sensor not available on this device.")
        }else{
            Log.e(TAG, "Light sensor available on this device.")

        }
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
        Log.d(TAG, "Sensor accuracy changed to: $accuracy")
    }

    override fun onSensorChanged(event: SensorEvent?) {
        if (event?.sensor?.type == Sensor.TYPE_LIGHT) {
          
            val luxValue = event.values[0]
            Log.d(TAG, "Current light level: $luxValue lux")
            val (status, color) = when {
                luxValue < 10 -> "Dark" to android.graphics.Color.BLACK
                luxValue < 100 -> "Semi-Dark" to android.graphics.Color.GRAY
                else -> "Light" to android.graphics.Color.WHITE
            }
          
            binding.tvLightSensorValue.text = "Light: $luxValue lux\nStatus: $status"

     
            if (status == "Dark") {
                binding.tvLightSensorValue.setTextColor(android.graphics.Color.WHITE)
            } else {
                binding.tvLightSensorValue.setTextColor(android.graphics.Color.BLACK)
            }

      
            binding.root.setBackgroundColor(color)
        }
    }

    override fun onResume() {
        super.onResume()
        // Register the listener for the light sensor
        lightSensor?.also { sensor ->
            sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_NORMAL)
        }
    }

    override fun onPause() {
        super.onPause()
        // Unregister the listener to stop receiving sensor updates
        sensorManager.unregisterListener(this)
    }
}
