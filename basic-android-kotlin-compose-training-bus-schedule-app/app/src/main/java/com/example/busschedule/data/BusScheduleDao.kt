package com.example.busschedule.data

import androidx.room.Dao
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface BusScheduleDao {
    @Query("SELECT * FROM Schedule")
    fun getSchedule(): Flow<List<BusSchedule>>

    @Query("SELECT * FROM Schedule WHERE stop_name=:name")
    fun getScheduleById(name: String): Flow<List<BusSchedule>>
}