package com.example.flightsearch

import androidx.compose.runtime.collectAsState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
fun searchAny(): Flow<Int> = flow{
    for (k in 1..3) {
        delay(100)
        emit(k)
    }
    delay(4000)
    for (k in 4..6) {
        delay(100)
        emit(k)
    }
}

suspend fun main() {
//    runBlocking {
//        launch {
//            val searchResult = searchAny()
//            searchResult.stateIn(
//                scope = this,
//                started = SharingStarted.WhileSubscribed(5000),
//                initialValue = MutableStateFlow(10)
//            ).collect{
//                    value -> println(value)
//            }
//        }
//    }
    listOf(1, 2, 3, 4, 5, 6).map {
        println(it)
    }
}