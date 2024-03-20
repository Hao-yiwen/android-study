package com.example.bookshelf.data

import com.example.bookshelf.network.BookShelfApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

interface AppContainer {
    val bookShelfRepository: BookShelfRepository
}

class DefaultContainer : AppContainer {
    val BASE_URL = "https://www.googleapis.com/books/v1/volumes"

    val retrofit =
        Retrofit.Builder().addConverterFactory(GsonConverterFactory.create()).baseUrl(BASE_URL)
            .build()

    val retrofitService: BookShelfApiService by lazy {
        retrofit.create(BookShelfApiService::class.java)
    }

    override val bookShelfRepository: BookShelfRepository =
        NetWorkBookShelfRepository(retrofitService)
}