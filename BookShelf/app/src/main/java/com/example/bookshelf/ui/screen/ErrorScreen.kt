package com.example.bookshelf.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.HorizontalAlignmentLine
import androidx.compose.ui.res.painterResource
import com.example.bookshelf.R

@Composable
fun ErrorScreen(modifier: Modifier){
    Column(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement =Arrangement.Center) {
        Image(painter = painterResource(id = R.drawable.ic_connection_error), contentDescription = null)
    }
}