package com.example.mycity.ui

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.mycity.R

@Composable
fun MyCityScreen(modifier: Modifier = Modifier) {
    Text(text = stringResource(id = R.string.app_name))
}