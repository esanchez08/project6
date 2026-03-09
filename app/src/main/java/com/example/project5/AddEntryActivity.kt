package com.example.project5

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.time.LocalDate

class AddEntryActivity : AppCompatActivity() {

    private lateinit var db: SleepDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_entry)

        db = SleepDatabase.getDatabase(this)

        val hoursInput = findViewById<EditText>(R.id.hoursInput)
        val saveButton = findViewById<Button>(R.id.saveButton)

        saveButton.setOnClickListener {
            val hours = hoursInput.text.toString().toDoubleOrNull()
            if (hours != null) {
                val entry = SleepEntry(
                    date = LocalDate.now().toString(),
                    startTime = "22:00",
                    endTime = "06:00",
                    hours = hours,
                    hoursSlept = hours
                )
                lifecycleScope.launch(Dispatchers.IO) {
                    db.sleepDao().insertEntry(entry)
                    finish()
                }
            } else {
                hoursInput.error = "Enter a valid number"
            }
        }
    }
}