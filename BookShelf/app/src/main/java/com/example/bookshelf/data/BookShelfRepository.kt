package com.example.bookshelf.data

import com.example.bookshelf.model.BookShelf
import com.example.bookshelf.network.BookShelfApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

interface BookShelfRepository {
    suspend fun getBookShelf(query: String): BookShelf
}

class NetWorkBookShelfRepository(private val bookShelfApiService: BookShelfApiService) :
    BookShelfRepository {
    override suspend fun getBookShelf(query: String): BookShelf {
        return bookShelfApiService.getBookShelf(query)
    }

}