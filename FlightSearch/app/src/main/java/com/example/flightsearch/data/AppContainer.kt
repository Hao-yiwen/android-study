package com.example.flightsearch.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore

interface AppContainer {
    val airportRepositoryProvider: AirportRepository
    val favoriteRepositoryProvider: FavoriteRepository
    val flightInputPreferencesRepository: FlightInputPreferencesRepository

    fun initDatabase(): Unit;
}

private const val FLIGHT_PREFERENCES = "flight_preferences"
private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(
    name = FLIGHT_PREFERENCES
)

class DefaultAppContainer(
    private val context: Context
) : AppContainer {
    override val airportRepositoryProvider: AirportRepository by lazy {
        AirportRepositoryProvider(FlightDataBase.getDataBase(context).airportDao())
    }

    override val favoriteRepositoryProvider: FavoriteRepository by lazy {
        FavoriteRepositoryProvider(FlightDataBase.getDataBase(context).favoriteDao())
    }

    override val flightInputPreferencesRepository: FlightInputPreferencesRepository by lazy {
        FlightInputPreferencesRepository(context.dataStore)
    }

    override fun initDatabase() {
        FlightDataBase.getDataBase(context)
    }
}