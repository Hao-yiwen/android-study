package com.example.kotlinandroid

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.kotlinandroid.page.Home
import com.example.kotlinandroid.ui.theme.KotlinAndroidTheme
import com.example.mylibrary2.Detail

class NavTestActivity:ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            KotlinAndroidTheme {
                val navController = rememberNavController()

                NavHost(navController = navController, startDestination = "home"){
                    composable("home"){
                        Home(navController)
                    }
                    composable("detail"){
                        Detail()
                    }
                }
            }
        }
    }
}