package com.example.helloworld

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        // Get data from intent
        val day = intent.getStringExtra("DAY")
        val maxTemp = intent.getIntExtra("MAX_TEMP", 0)
        val minTemp = intent.getIntExtra("MIN_TEMP", 0)
        val condition = intent.getStringExtra("CONDITION")

        // Display data
        val dayView: TextView = findViewById(R.id.dayText)
        val maxTempView: TextView = findViewById(R.id.maxTempText)
        val minTempView: TextView = findViewById(R.id.minTempText)
        val conditionView: TextView = findViewById(R.id.conditionText)

        dayView.text = day
        maxTempView.text = "Max Temp: $maxTemp°C"
        minTempView.text = "Min Temp: $minTemp°C"
        conditionView.text = "Condition: $condition"
    }
}
