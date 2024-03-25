package com.example.flightsearch.data

import androidx.room.Dao
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface AirportDao {
    @Query("SELECT * FROM airport WHERE iata_code LIKE :str")
    fun searchAirport(str: String): Flow<List<Airport>>

    @Query("SELECT * FROM airport")
    fun searchAllAirport(): Flow<List<Airport>>
}