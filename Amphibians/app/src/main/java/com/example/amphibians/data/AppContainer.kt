package com.example.amphibians.data

import com.example.amphibians.network.AmohibiansApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

interface AppContainer {
    val amphibiansRepository: AmohibiansRepository
}

class DefaultContainer : AppContainer {
    private val BASE_URL = "https://android-kotlin-fun-mars-server.appspot.com/"

    private val retrofit =
        Retrofit.Builder().addConverterFactory(GsonConverterFactory.create()).baseUrl(BASE_URL)
            .build()

    private val retrofitService: AmohibiansApiService by lazy {
        retrofit.create(AmohibiansApiService::class.java)
    }

    override val amphibiansRepository: AmohibiansRepository by lazy {
        NetWorkAmohibiansRepository(retrofitService)
    }
}