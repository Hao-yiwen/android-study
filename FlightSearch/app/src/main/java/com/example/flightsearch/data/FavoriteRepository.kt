package com.example.flightsearch.data

import kotlinx.coroutines.flow.Flow

interface FavoriteRepository {
    suspend fun insert(favorite: Favorite)

    suspend fun delete(favorite: Favorite)

    fun getAllFavorite(): Flow<List<Favorite>>
}

class FavoriteRepositoryProvider(val favoriteDao: FavoriteDao) : FavoriteRepository {
    override suspend fun insert(favorite: Favorite) {
        favoriteDao.insert(favorite)
    }

    override suspend fun delete(favorite: Favorite) {
        favoriteDao.delete(favorite)
    }

    override fun getAllFavorite(): Flow<List<Favorite>> {
        return favoriteDao.getAllFavorite()
    }
}