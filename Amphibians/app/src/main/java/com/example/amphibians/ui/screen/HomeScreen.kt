package com.example.amphibians.ui.screen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.amphibians.R
import com.example.amphibians.state.AmohibiansListType
import com.example.amphibians.state.AmohibiansViewModel

@Composable
fun HomeScreen(viewModel: AmohibiansViewModel, modifier: Modifier = Modifier) {
    val uiState = viewModel.uiState.collectAsState()

    when (uiState.value.amohibiansList) {
        is AmohibiansListType.Loading -> LoadingScreen(modifier = modifier.fillMaxSize())
        is AmohibiansListType.Error -> ErrorScreen(
            modifier = modifier.fillMaxSize(),
            retryAction = { viewModel.getAmphibiansList() })

        is AmohibiansListType.Success -> AmohibiansScreen(
            amohibiansList = (uiState.value.amohibiansList as AmohibiansListType.Success).amohibiansList,
            modifier = modifier
        )

        else -> LoadingScreen(modifier = Modifier)
    }
}