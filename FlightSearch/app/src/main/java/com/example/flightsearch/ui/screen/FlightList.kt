package com.example.flightsearch.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.flightsearch.data.FavoriteContainer

@Composable
fun FlightList(modifier: Modifier, uiState: HomeScreenUiState, viewModel: HomeScreenViewModel) {
    Column {
        Text(
            text = if (uiState.searchResult.isNotEmpty() && uiState.searchStr.isNotEmpty()) "Search Result" else "FavoriteContainer",
            style = MaterialTheme.typography.displayLarge,
            modifier = modifier.padding(16.dp)
        )
        if (uiState.searchResult.isNotEmpty() && uiState.searchStr.isNotEmpty()) {
            LazyColumn {
                items(uiState.searchAirportMap) {
                    FlightCard(
                        modifier
                            .fillMaxWidth()
                            .padding(bottom = 16.dp, start = 16.dp, end = 16.dp),
                        it,
                        viewModel
                    )
                }
            }
        } else {
            LazyColumn {
                items(uiState.favoriteAirports) {
                    FlightCard(
                        modifier
                            .fillMaxWidth()
                            .padding(bottom = 16.dp, start = 16.dp, end = 16.dp),
                        it,
                        viewModel
                    )
                }
            }
        }
    }
}

@Composable
fun FlightCard(modifier: Modifier, favorite: FavoriteContainer, viewModel: HomeScreenViewModel) {
    Card(modifier = modifier) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .weight(1f)
            ) {
                Text(text = "DEPART", style = MaterialTheme.typography.displaySmall)
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(10.dp)
                ) {
                    Text(
                        text = favorite.departureCode,
                        style = MaterialTheme.typography.displayMedium
                    )
                    Text(
                        text = favorite.departureName,
                        modifier = Modifier.padding(start = 10.dp),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
                Text(text = "ARRIVE", style = MaterialTheme.typography.displaySmall)
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(10.dp)
                ) {
                    Text(
                        text = favorite.destinationCode,
                        style = MaterialTheme.typography.displayMedium
                    )
                    Text(
                        text = favorite.destinationName,
                        modifier = Modifier.padding(start = 10.dp),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }
            IconButton(onClick = {
                viewModel.updateFavoriteAirport(favorite)
            }) {
                Icon(
                    imageVector = if (favorite.isFavorite) Icons.Filled.Star else Icons.Outlined.Star,
                    tint = if (favorite.isFavorite) Color.Yellow else LocalContentColor.current,
                    contentDescription = "",
                    modifier = Modifier
                        .size(55.dp)
                        .padding(end = 15.dp)
                )
            }
        }
    }
}