package com.example.flightsearch.data

import kotlinx.coroutines.flow.Flow

interface AirportRepository {
    fun searchAirport(str: String): Flow<List<Airport>>

    suspend fun searchAirportAsync(str: String): List<Airport>

    fun searchAllAirport(): Flow<List<Airport>>

    suspend fun searchAllAirportAsync(): List<Airport>
}

class AirportRepositoryProvider(val airportDao: AirportDao) : AirportRepository {
    override fun searchAirport(str: String): Flow<List<Airport>> {
        return airportDao.searchAirport(str)
    }

    override suspend fun searchAirportAsync(str: String): List<Airport> {
        return airportDao.searchAirportAsync(str)
    }

    override fun searchAllAirport(): Flow<List<Airport>> {
        return airportDao.searchAllAirport()
    }

    override suspend fun searchAllAirportAsync(): List<Airport> {
        return airportDao.searchAllAirportAsync()
    }
}