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
}