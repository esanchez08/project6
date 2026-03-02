package com.example.project5

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "sleep_entries")
data class SleepEntry(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val date: String,
    val hoursSlept: Double
)