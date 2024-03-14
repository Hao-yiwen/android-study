package com.example.mycity.kotlin

import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import java.lang.AssertionError

fun main() {
    runBlocking {
        println("weather forecast")
        println(getWeatherReport1())
        println("Have a good day")
    }
}

suspend fun getWeatherReport1() = coroutineScope {
    val forecast: Deferred<String> = async { getForecast1() }
    val tempatire: Deferred<String> = async {
        try {
            getTemperature1()
        } catch (e: AssertionError) {
            println("Caught exception $e")
            "{No temperture found}"
        }
    }
    delay(2000)
    tempatire.cancel()
    " ${forecast.await()}"
}

suspend fun getForecast1(): String {
    delay(1000)
    return "sunny"
}

suspend fun getTemperature1(): String {
    delay(1000)
//    throw AssertionError("Temperature is invalid")
    return "30\u00b0C"
}