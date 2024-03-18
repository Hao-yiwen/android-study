package com.example.amphibians.network

import com.example.amphibians.model.Amohibians
import retrofit2.http.GET

interface AmohibiansApiService{
    @GET("amphibians")
    suspend fun getAmphibians(): List<Amohibians>
}