package com.example.flightsearch.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp

@Composable
fun FlightList(modifier: Modifier,uiState: HomeScreenUiState) {
    Column {
        Text(
            text = "Flight list",
            style = MaterialTheme.typography.displayLarge,
            modifier = modifier.padding(16.dp)
        )
        FlightCard(
            modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp, start = 16.dp, end = 16.dp)
        )
    }
}

@Composable
fun FlightCard(modifier: Modifier) {
    Card(modifier = modifier) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Column(modifier = Modifier.padding(16.dp).weight(1f)) {
                Text(text = "DEPART", style = MaterialTheme.typography.displaySmall)
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(10.dp)
                ) {
                    Text(text = "FCO", style = MaterialTheme.typography.displayMedium)
                    Text(
                        text = "Leconardo da Vinci International Airport",
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
                    Text(text = "JFK", style = MaterialTheme.typography.displayMedium)
                    Text(
                        text = "John F. Kennedy International Airport",
                        modifier = Modifier.padding(start = 10.dp),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }
            Icon(
                imageVector = Icons.Filled.Star,
                contentDescription = "",
                modifier = Modifier
                    .size(55.dp)
                    .padding(end = 15.dp)
            )
        }
    }
}