package com.example.bookshelf.network

import com.example.bookshelf.model.BookShelf
import retrofit2.http.GET

interface BookShelfApiService {
    @GET("bookshelf")
    suspend fun getBookShelf(): List<BookShelf>
}