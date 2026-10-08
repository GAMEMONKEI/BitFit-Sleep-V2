package com.example.bitfit
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.ColumnInfo


@Entity(tableName = "sleep_entry")
data class SleepEntry(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    @ColumnInfo(name = "date") val date: String,
    @ColumnInfo(name = "hours") val hours: Int,
    @ColumnInfo(name = "minutes") val mins: Int,
)