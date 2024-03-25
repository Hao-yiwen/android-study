package com.example.flightsearch.data

import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException

class FlightInputPreferencesRepository(private val dataStore: DataStore<Preferences>) {
    companion object {
        const val TAG = "FlightPerferencesRepo"
        val SEARCH_STR = stringPreferencesKey("search_str")
    }

    val searchStr: Flow<String> = dataStore.data
        .catch {
            if (it is IOException) {
                Log.d(TAG, "Error reading preferences", it)
                emit(emptyPreferences())
            } else {
                throw it
            }
        }
        .map {
            it[SEARCH_STR] ?: ""
        }

    suspend fun saveLayoutPreferences(searchStr: String) {
        dataStore.edit { preferences ->
            preferences[SEARCH_STR] = searchStr
        }
    }
}