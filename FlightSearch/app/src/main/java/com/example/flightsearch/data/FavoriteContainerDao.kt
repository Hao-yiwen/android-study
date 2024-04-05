package com.example.flightsearch.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteContainerDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(favoriteContainer: FavoriteContainer)

    @Delete
    suspend fun delete(favoriteContainer: FavoriteContainer)

    @Query("SELECT * FROM favoriteContainer")
    fun getAllFavorite(): Flow<List<FavoriteContainer>>

    @Query("SELECT * FROM favoriteContainer")
    suspend fun getAllFavoriteAsync(): List<FavoriteContainer>
}