package com.example.bitfit

import android.app.DatePickerDialog
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import java.util.Calendar

class AddEntryActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_entry)

        val dateButton = findViewById<Button>(R.id.dateButton)
        val hoursEditText = findViewById<EditText>(R.id.hoursEditText)
        val minutesEditText = findViewById<EditText>(R.id.minutesEditText)
        val logDataButton = findViewById<Button>(R.id.logDataButton)

        var selectedDate = ""

        dateButton.setOnClickListener {
            val calendar = Calendar.getInstance()

            val datePicker = DatePickerDialog(
                this,
                { _, year, month, day ->
                    selectedDate = "${month + 1}/$day/$year"
                    dateButton.text = selectedDate
                },
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
            )

            datePicker.show()
        }

        logDataButton.setOnClickListener {

            val hours = hoursEditText.text.toString().toIntOrNull()
            val minutes = minutesEditText.text.toString().toIntOrNull()

            if (selectedDate.isEmpty() || hours == null || minutes == null) {
                Toast.makeText(
                    this,
                    "Please fill out all fields",
                    Toast.LENGTH_SHORT
                ).show()

                return@setOnClickListener
            }

            if (minutes !in 0..59) {
                Toast.makeText(
                    this,
                    "Minutes must be between 0 and 59",
                    Toast.LENGTH_SHORT
                ).show()

                return@setOnClickListener
            }

            val sleepEntry = SleepEntry(
                date = selectedDate,
                hours = hours,
                mins = minutes
            )

            lifecycleScope.launch(IO) {
                val database = (application as BitFitApplication).db

                database.sleepEntryDao().insert(sleepEntry)

                runOnUiThread {
                    finish()
                }
            }
        }
    }
}