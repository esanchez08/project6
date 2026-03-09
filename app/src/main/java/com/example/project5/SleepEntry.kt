package com.example.project5

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "sleep_entries")
data class SleepEntry(

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    val date: String,

    val startTime: String,

    val endTime: String,

    val hours: Double,

    val hoursSlept: Double
)