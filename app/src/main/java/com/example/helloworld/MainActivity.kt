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
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    // Parallel arrays for weather data
    private val days = mutableListOf("Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday")
    private val maxTemps = mutableListOf(30, 32, 33, 29, 28, 27, 31)
    private val minTemps = mutableListOf(20, 21, 19, 18, 22, 23, 24)
    private val conditions = mutableListOf("Sunny", "Rainy", "Cloudy", "Sunny", "Rainy", "Sunny", "Cloudy")

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Calculate and display average temperature
        updateAverageTemperature()

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

        // Handle Add button
        val addButton: Button = findViewById(R.id.addButton)
        addButton.setOnClickListener {
            addNewEntry(adapter)
        }

        // Handle Clear button
        val clearButton: Button = findViewById(R.id.clearButton)
        clearButton.setOnClickListener {
            clearAllEntries(adapter)
        }
    }

    private fun updateAverageTemperature() {
        val totalTemp = maxTemps.sum() + minTemps.sum()
        val avgTemp = if (days.isNotEmpty()) totalTemp / (days.size * 2) else 0
        val avgTempView: TextView = findViewById(R.id.avgTemp)
        avgTempView.text = "Average Temperature: $avgTemp°C"
    }

    private fun addNewEntry(adapter: ArrayAdapter<String>) {
        val dayInput: EditText = findViewById(R.id.dayInput)
        val maxTempInput: EditText = findViewById(R.id.maxTempInput)
        val minTempInput: EditText = findViewById(R.id.minTempInput)
        val conditionInput: EditText = findViewById(R.id.conditionInput)

        val day = dayInput.text.toString()
        val maxTemp = maxTempInput.text.toString().toIntOrNull()
        val minTemp = minTempInput.text.toString().toIntOrNull()
        val condition = conditionInput.text.toString()

        if (day.isNotEmpty() && maxTemp != null && minTemp != null && condition.isNotEmpty()) {
            days.add(day)
            maxTemps.add(maxTemp)
            minTemps.add(minTemp)
            conditions.add(condition)

            adapter.notifyDataSetChanged() // Update ListView
            updateAverageTemperature()    // Recalculate average temperature

            // Clear input fields
            dayInput.text.clear()
            maxTempInput.text.clear()
            minTempInput.text.clear()
            conditionInput.text.clear()
        } else {
            Toast.makeText(this, "Please enter all fields correctly", Toast.LENGTH_SHORT).show()
        }
    }

    private fun clearAllEntries(adapter: ArrayAdapter<String>) {
        days.clear()
        maxTemps.clear()
        minTemps.clear()
        conditions.clear()

        adapter.notifyDataSetChanged() // Update ListView
        updateAverageTemperature()    // Reset average temperature
    }
}