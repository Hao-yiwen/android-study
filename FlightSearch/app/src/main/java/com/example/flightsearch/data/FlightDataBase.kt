package com.example.flightsearch.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [Airport::class, Favorite::class, FavoriteContainer::class],
    version = 4,
    exportSchema = false
)
abstract class FlightDataBase : RoomDatabase() {
    abstract fun airportDao(): AirportDao;

    abstract fun favoriteContainerDao(): FavoriteContainerDao;

    companion object {
        @Volatile
        private var Instance: FlightDataBase? = null

        fun getDataBase(context: Context): FlightDataBase {
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(context, FlightDataBase::class.java, "app_database")
                    .createFromAsset("flight_search.db")
                    /**
                     * @exception 我的场景是根据flight_search.db创建数据库，然后再根据dao创建一个表，但是使用fallbackToDestructiveMigration后每次
                     * 打开app都会导致应用数据库重构，所以在这里关闭数据库，如果在开发阶段需要重构数据库，可以打开这个注释，
                     * 目前问题并没有得到定位，但是fallbackToDestructiveMigration方法确实要少用。。。。
                     */
//                    .fallbackToDestructiveMigration()
                    .build().also { Instance = it }
            }
        }
    }
}