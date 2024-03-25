package com.example.flightsearch.data

import kotlinx.coroutines.flow.Flow

interface AirportRepository {
    fun searchAirport(str: String): Flow<List<Airport>>

    fun searchAllAirport(): Flow<List<Airport>>
}

class AirportRepositoryProvider(val airportDao: AirportDao) : AirportRepository {
    override fun searchAirport(str: String): Flow<List<Airport>> {
        return airportDao.searchAirport(str)
    }

    override fun searchAllAirport(): Flow<List<Airport>> {
        return airportDao.searchAllAirport()
    }
}