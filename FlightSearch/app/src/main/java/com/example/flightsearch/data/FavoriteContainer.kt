package com.example.flightsearch.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull

@Entity(tableName = "favoriteContainer")
data class FavoriteContainer(
    @PrimaryKey
    val id: Int,
    @NotNull
    @ColumnInfo(name = "departure_code")
    val departureCode: String,
    @NotNull
    @ColumnInfo(name = "departure_name")
    val departureName: String,
    @NotNull
    @ColumnInfo(name = "destination_code")
    val destinationCode: String,
    @NotNull
    @ColumnInfo(name = "destination_name")
    val destinationName: String,
    @NotNull
    @ColumnInfo(name = "is_favorite")
    val isFavorite: Boolean = false
)