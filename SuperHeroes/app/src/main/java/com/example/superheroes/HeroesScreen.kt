package com.example.superheroes

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.VerticalAlignmentLine
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.superheroes.model.HeroesRepository
import com.example.superheroes.ui.theme.SuperHeroesTheme

@Composable
fun HeroesScreen(modifier: Modifier = Modifier) {
    Scaffold(topBar = {
        HeroesTarbar()
    }) { it ->
        LazyColumn(contentPadding = it) {
            items(HeroesRepository.heros) {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp, end = 16.dp, top = 8.dp, bottom = 8.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .background(MaterialTheme.colorScheme.secondaryContainer)
                            .padding(16.dp)
                    ) {
                        Column(
                            modifier = Modifier.weight(1f)
                        ) {
                            Text(
                                text = stringResource(id = it.nameRes),
                                style = MaterialTheme.typography.displaySmall,
                            )
                            Text(
                                text = stringResource(id = it.descriptionRes),
                                style = MaterialTheme.typography.bodyLarge
                            )
                        }
                        Box(modifier = Modifier.padding(start = 16.dp)) {
                            Image(
                                painter = painterResource(id = it.imageRes),
                                contentDescription = null,
                                modifier = Modifier
                                    .width(72.dp)
                                    .height(72.dp)
                                    .clip(MaterialTheme.shapes.small),
                                contentScale = ContentScale.Crop,
                            )
                        }
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HeroesTarbar() {
    CenterAlignedTopAppBar(title = {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = stringResource(id = R.string.app_name),
                style = MaterialTheme.typography.displayLarge
            )
        }
    }, modifier = Modifier.fillMaxWidth())
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    SuperHeroesTheme {
        HeroesScreen(modifier = Modifier)
    }
}