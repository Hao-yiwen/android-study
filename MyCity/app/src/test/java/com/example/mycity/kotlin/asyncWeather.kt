package com.example.mycity.kotlin

import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

fun main() {
    runBlocking {
        println("weather forecast")
//        async模式
//        val forecast: Deferred<String> = async {
//            getForecast()
//        }
//        val temperature: Deferred<String> = async {
//            getTemperature()
//        }
//        println("${forecast.await()} ${temperature.await()}")
        /**
         * 并行分解
         */
        println(getWeatherReport())
        println("Have a good day")
    }
}

suspend fun getWeatherReport() = coroutineScope {
    val forecast: Deferred<String> = async { getForecast() }
    val tempatire: Deferred<String> = async { getTemperature() }
    "${forecast.await()} ${tempatire.await()}"
}

suspend fun getForecast(): String {
    delay(1000)
    return "sunny"
}

suspend fun getTemperature(): String {
    delay(1000)
    return "30\u00b0C"
}