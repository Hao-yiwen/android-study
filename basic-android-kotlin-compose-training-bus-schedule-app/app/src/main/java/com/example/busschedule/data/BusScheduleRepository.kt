package com.example.busschedule.data

import kotlinx.coroutines.flow.Flow

interface BusScheduleRepository {
    fun getSchedule(): Flow<List<BusSchedule>>

    fun getScheduleById(name: String): Flow<List<BusSchedule>>
}