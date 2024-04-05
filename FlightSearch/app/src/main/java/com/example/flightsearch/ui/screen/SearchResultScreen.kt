package com.example.flightsearch.ui.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun SearchResultScreen(uiState: HomeScreenUiState, viewModel: HomeScreenViewModel) {
    LazyColumn {
        items(uiState.searchResult) { item ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .padding(top = 16.dp)
                    .clickable {
                        viewModel.selectAirport(item)
                    },
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = item.iataCode, style = MaterialTheme.typography.displayMedium)
                Text(text = item.name, modifier = Modifier.padding(start = 16.dp))
            }
        }
    }
}