package com.example.bookshelf.network

import com.example.bookshelf.model.BookShelf
import retrofit2.http.GET
import retrofit2.http.Query

interface BookShelfApiService {
    @GET(".")
    suspend fun getBookShelf(@Query("q") query: String): BookShelf
}