package com.example.mycity.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.mycity.R
import com.example.mycity.ui.utils.MyCityContentType
import com.example.mycity.ui.utils.MyCityPageType

@Composable
fun MyCityApp(windowSize: WindowWidthSizeClass) {
    val viewModel: MyCityViewModel = viewModel()
    val uiState = viewModel.uiState.collectAsState().value
    val contentType: MyCityContentType

    when (windowSize) {
        WindowWidthSizeClass.Expanded -> {
            contentType = MyCityContentType.LIST_AND_DETAIL
        }

        else -> {
            contentType = MyCityContentType.LIST_ONLY
        }
    }

    Scaffold(topBar = {
        MyCityTopBar(viewModel, uiState)
    }) { paddingValues ->
        MyCityScreen(
            contentType=contentType,
            myCityUiState = uiState,
            myCityViewModel = viewModel,
            modifier = Modifier.padding(paddingValues)
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyCityTopBar(myCityViewModel: MyCityViewModel, myCityUiState: MyCityUiState) {
    CenterAlignedTopAppBar(
        navigationIcon = {
            if (myCityUiState.currentShowPage == MyCityPageType.FOOD_PAGE) {
                Box {}
            } else {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    IconButton(onClick = { myCityViewModel.onClickBack() }) {
                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = null)
                    }
                }
            }
        },
        title = {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(text = stringResource(id = R.string.app_name))
            }
        },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(MaterialTheme.colorScheme.primaryContainer),
    )
}