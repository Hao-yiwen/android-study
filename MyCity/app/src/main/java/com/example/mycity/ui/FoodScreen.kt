package com.example.mycity.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.mycity.R
import com.example.mycity.data.FoodDataProvider
import com.example.mycity.model.Food

@Composable
fun FoodScreen(myCityViewModel: MyCityViewModel, myCityUiState: MyCityUiState, modifier: Modifier) {
    val foods = FoodDataProvider.defaultFood
    val currentFood = myCityUiState.currentFood
    LazyColumn(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(
            dimensionResource(id = R.dimen.padding_large)
        ),
    ) {
        items(foods, key = { food -> food.id }) {
            val isSelected = it == currentFood
            FoodItem(
                food = it,
                isSelected = isSelected,
                modifier = Modifier
                    .clickable {
                        myCityViewModel.updateCurrentFood(it)
                    }
            )
        }
    }
}

@Composable
fun FoodItem(food: Food, isSelected: Boolean, modifier: Modifier) {
    Card(modifier = modifier) {
        Row(
            modifier = Modifier
                .then(if (isSelected) Modifier.background(MaterialTheme.colorScheme.primaryContainer) else Modifier)
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = food.image),
                contentDescription = null,
                modifier = Modifier
                    .height(100.dp)
                    .width(100.dp)
            )
            Column(modifier = Modifier.padding(start = 16.dp)) {
                Text(
                    text = stringResource(id = food.name),
                    style = MaterialTheme.typography.displayLarge
                )
                Text(text = stringResource(id = food.describe))
            }
        }
    }
}