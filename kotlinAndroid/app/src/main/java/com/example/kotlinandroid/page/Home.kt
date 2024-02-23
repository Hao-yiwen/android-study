package com.example.kotlinandroid.page

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController

@Composable
fun Home(navController:NavController){
    Button(onClick = {
        navController.navigate("detail")
    }) {
        Text(text = "Home")
    }
}