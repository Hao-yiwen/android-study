package com.example.busschedule.data

import kotlinx.coroutines.flow.Flow

class offlineBusScheduleRepository(private val busScheduleDao: BusScheduleDao) :
    BusScheduleRepository {
    override fun getSchedule(): Flow<List<BusSchedule>> {
        return busScheduleDao.getSchedule()
    }

    override fun getScheduleById(name: String): Flow<List<BusSchedule>> {
        return busScheduleDao.getScheduleById(name)
    }

}