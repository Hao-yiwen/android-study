package com.example.flightsearch

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.tooling.preview.Preview
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

val myFlow: Flow<Int> = flow {
    emit(1)
    delay(1000)
    emit(2)
    delay(1000)
    emit(3)
}

@Composable
fun TestView() {
    // 使用 collectAsState() 来观察 Flow，并在 UI 中展示值
    // 提供一个初始值，以便在 Flow 开始发射值之前使用
    val currentValue = myFlow.collectAsState(initial = 0).value

    Text(text = "当前值: $currentValue")
}

@Preview
@Composable
fun MyComponentPreview() {
    TestView()
}
