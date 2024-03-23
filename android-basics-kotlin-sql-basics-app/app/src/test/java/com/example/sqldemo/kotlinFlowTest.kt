package com.example.sqldemo

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() {
    runBlocking {
        launch {
            for (k in 1..3) {
                println("I'm not blocked $k")
                delay(100)
            }
        }
        simple().collect { value -> println(value) }
    }
}

fun simple(): Flow<Int> = flow{
    for (k in 1..3) {
//        delay(100)
        emit(k)
    }
}