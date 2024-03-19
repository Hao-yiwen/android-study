package com.example.bookshelf.ui.screen

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.bookshelf.R

@Composable
fun HomeScreen(modifier: Modifier = Modifier) {
    Text(text = stringResource(id = R.string.app_name))
}