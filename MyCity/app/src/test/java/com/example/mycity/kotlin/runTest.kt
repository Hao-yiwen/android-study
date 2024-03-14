package com.example.mycity.kotlin

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.job
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

fun main(){
    runBlocking {
        println("${Thread.currentThread().name} - runBlocking functuon")
        launch {
            println("${Thread.currentThread().name} - runBlocking functuon")
            withContext(Dispatchers.Default){
                println("${Thread.currentThread().name} - runBlocking functuon")
                delay(1000)
                println("World!")
            }
            println("${Thread.currentThread().name} - runBlocking functuon")
        }
        println("Loading...")
    }
}