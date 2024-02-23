package com.example.mylibrary

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController


@Composable
fun Detail(navController: NavController){
    Button(onClick = {
        navController.popBackStack()
    }) {
        Text(text = "Detail")
    }
}