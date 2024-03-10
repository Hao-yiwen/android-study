package com.example.mycity.ui

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.mycity.R

@Composable
fun MyCityApp() {
    val viewModel: MyCityViewModel = viewModel()
    val uiState = viewModel.uiState.collectAsState().value
//    val navController: NavHostController = rememberNavController()

    Scaffold(topBar = {
        MyCityTopBar()
    }) { paddingValues ->
        MyCityScreen(
            myCityUiState = uiState,
            myCityViewModel = viewModel,
            modifier = Modifier.padding(paddingValues)
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyCityTopBar() {
    CenterAlignedTopAppBar(
        title = {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(text = stringResource(id = R.string.app_name))
            }
        },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(MaterialTheme.colorScheme.primaryContainer),
    )
}