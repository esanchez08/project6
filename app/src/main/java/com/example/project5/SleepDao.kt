package com.example.project5

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface SleepDao {

    @Insert
    suspend fun insertEntry(entry: SleepEntry)

    @Query("SELECT * FROM sleep_entries ORDER BY id DESC")
    suspend fun getAllEntries(): List<SleepEntry>

    // Dashboard statistics

    @Query("SELECT AVG(hours) FROM sleep_entries")
    suspend fun getAverageSleep(): Double?

    @Query("SELECT MIN(hours) FROM sleep_entries")
    suspend fun getMinSleep(): Double?

    @Query("SELECT MAX(hours) FROM sleep_entries")
    suspend fun getMaxSleep(): Double?
}