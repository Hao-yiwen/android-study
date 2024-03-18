package com.example.amphibians.data

import com.example.amphibians.model.Amohibians
import com.example.amphibians.network.AmohibiansApiService

interface AmohibiansRepository {
    suspend fun getAmohibians(): List<Amohibians>
}

class NetWorkAmohibiansRepository(private val amohibiansApiService: AmohibiansApiService) :
    AmohibiansRepository {
    override suspend fun getAmohibians(): List<Amohibians> {
        return amohibiansApiService.getAmphibians()
    }

}