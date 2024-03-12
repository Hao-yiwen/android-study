package com.example.mycity.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.mycity.R
import com.example.mycity.model.Place

@Composable
fun PlaceOfFoodScreen(
    myCityViewModel: MyCityViewModel, myCityUiState: MyCityUiState, modifier: Modifier
) {
    val currentPlace = myCityUiState.currentPlace
    val currentFoodPlace: List<Place> = myCityUiState.currentFoodPlaces
    LazyColumn(modifier = modifier) {
        items(currentFoodPlace, key = { foodPlace -> foodPlace.id }) {
            val isSelected = it == currentPlace
            PlaceItem(place = it, isSelected = isSelected, modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    myCityViewModel.updateCurrentPlace(it)
                }
                .padding(top = dimensionResource(id = R.dimen.padding_large)))
        }
    }
}

@Composable
fun PlaceItem(place: Place, isSelected: Boolean, modifier: Modifier) {
    Card(modifier = modifier) {
        Row(
            modifier = Modifier
                .then(
                    if (isSelected) Modifier.background(MaterialTheme.colorScheme.primaryContainer) else Modifier
                )
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = place.image),
                contentDescription = null,
                modifier = Modifier
                    .height(100.dp)
                    .width(100.dp)
            )
            Column(modifier = Modifier.padding(start = 16.dp)) {
                Text(
                    text = stringResource(id = place.name),
                    style = MaterialTheme.typography.displayLarge
                )
                Text(text = stringResource(id = place.location))
            }
        }
    }
}