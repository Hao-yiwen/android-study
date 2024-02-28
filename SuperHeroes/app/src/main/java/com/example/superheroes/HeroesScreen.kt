package com.example.superheroes

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.superheroes.model.HeroesRepository

@Composable
fun HeroesScreen(modifier: Modifier = Modifier) {
    LazyColumn{
        items(HeroesRepository.heros){
            Card(modifier = Modifier) {
                Text(text = "hello world")
            }
        }
    }
}