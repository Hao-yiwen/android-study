package com.example.bookshelf.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.bookshelf.R
import com.example.bookshelf.ui.screen.HomeScreen

@Composable
fun BookShelfApp() {
    Scaffold(topBar = {
        BookShelfTopBar()
    }) {
        HomeScreen(modifier = Modifier.padding(it))
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookShelfTopBar() {
    CenterAlignedTopAppBar(
        title = { Text(text = stringResource(id = R.string.app_name)) },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(MaterialTheme.colorScheme.primary)
    )
}