package com.example.helloworld

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    // Parallel arrays for weather data
    private val days = arrayOf("Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday")
    private val maxTemps = intArrayOf(30, 32, 33, 29, 28, 27, 31)
    private val minTemps = intArrayOf(20, 21, 19, 18, 22, 23, 24)
    private val conditions = arrayOf("Sunny", "Rainy", "Cloudy", "Sunny", "Rainy", "Sunny", "Cloudy")
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Calculate average temperature
        val totalTemp = maxTemps.sum() + minTemps.sum()
        val avgTemp = totalTemp / (maxTemps.size * 2)

        val avgTempView: TextView = findViewById(R.id.avgTemp)
        avgTempView.text = "Average Temperature: $avgTemp°C"

        // Set up ListView
        val listView: ListView = findViewById(R.id.listView)
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, days)
        listView.adapter = adapter

        // Handle item clicks
        listView.setOnItemClickListener { _, _, position, _ ->
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra("DAY", days[position])
            intent.putExtra("MAX_TEMP", maxTemps[position])
            intent.putExtra("MIN_TEMP", minTemps[position])
            intent.putExtra("CONDITION", conditions[position])
            startActivity(intent)
        }
    }
}