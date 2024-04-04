package com.example.flightsearch.ui.screen

import android.text.Editable.Factory
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.flightsearch.R
import kotlinx.coroutines.coroutineScope

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    modifier: Modifier,
    viewModel: HomeScreenViewModel = viewModel(factory = HomeScreenViewModel.Factory)
) {
    val uiState by viewModel.uiState.collectAsState()
    var expanded by remember { mutableStateOf(false) }
    val focusRequester = remember { FocusRequester() }

    LaunchedEffect(Unit) {
        focusRequester.requestFocus() // 组件加载时请求焦点
    }

    Column {
        TextField(value = uiState.searchStr,
            onValueChange = {
                viewModel.changeStr(it)
                viewModel.searchAirport(it)
                expanded = it.isNotEmpty()
            },
            modifier = modifier
                .fillMaxWidth()
                .padding(10.dp)
                .focusRequester(focusRequester), // 使用FocusRequester,
            shape = RoundedCornerShape(60.dp),
            colors = TextFieldDefaults.colors(
                cursorColor = Color.Black, // 设置光标颜色
                disabledLabelColor = Color.Transparent, // 设置标签禁用时的颜色
                focusedIndicatorColor = Color.Transparent, // 设置焦点时下划线的颜色（这里使用透明色隐藏它）
                unfocusedIndicatorColor = Color.Transparent, // 设置非焦点时下划线的颜色（同样使用透明色隐藏它）
                disabledIndicatorColor = Color.Transparent // 设置禁用状态下下划线的颜色（透明色）
            ),
            prefix = {
                Icon(imageVector = Icons.Filled.Search, contentDescription = "Search")
            },
            placeholder = {
                Text(text = "请输出搜索关键词")
            })
        DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
            uiState.searchResult.forEach { item ->
                DropdownMenuItem(text = {
                    Text(text = item.iataCode)
                }, onClick = {

                })
            }
        }
        FlightList(modifier = Modifier, uiState = uiState)
    }
}