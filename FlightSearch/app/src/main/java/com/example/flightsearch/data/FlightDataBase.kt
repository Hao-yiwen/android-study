package com.example.flightsearch.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Airport::class, Favorite::class], version = 2, exportSchema = false)
abstract class FlightDataBase : RoomDatabase() {
    abstract fun airportDao(): AirportDao;

    abstract fun favoriteDao(): FavoriteDao;

    companion object {
        @Volatile
        private var Instance: FlightDataBase? = null

        fun getDataBase(context: Context): FlightDataBase {
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(context, FlightDataBase::class.java, "app_database")
                    .createFromAsset("flight_search.db")
                    .fallbackToDestructiveMigration()
                    .build().also { Instance = it }
            }
        }
    }
}