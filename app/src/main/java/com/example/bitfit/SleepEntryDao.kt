package com.example.bitfit

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface SleepEntryDao {

    @Query("SELECT * FROM sleep_entry")
    fun getAll(): Flow<List<SleepEntry>>

    @Insert
    fun insert(sleepEntry: SleepEntry)

}