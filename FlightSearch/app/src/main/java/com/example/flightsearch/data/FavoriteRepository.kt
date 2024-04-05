package com.example.flightsearch.data

import kotlinx.coroutines.flow.Flow

interface FavoriteRepository {
    suspend fun insert(favorite: FavoriteContainer)

    suspend fun delete(favorite: FavoriteContainer)

    fun getAllFavorite(): Flow<List<FavoriteContainer>>

    suspend fun getAllFavoriteAsync(): List<FavoriteContainer>
}

class FavoriteRepositoryProvider(val favoriteContainerDao: FavoriteContainerDao) : FavoriteRepository {
    override suspend fun insert(favorite: FavoriteContainer) {
        favoriteContainerDao.insert(favorite)
    }

    override suspend fun delete(favorite: FavoriteContainer) {
        favoriteContainerDao.delete(favorite)
    }

    override fun getAllFavorite(): Flow<List<FavoriteContainer>> {
        return favoriteContainerDao.getAllFavorite()
    }

    override suspend fun getAllFavoriteAsync(): List<FavoriteContainer> {
        return favoriteContainerDao.getAllFavoriteAsync()
    }
}