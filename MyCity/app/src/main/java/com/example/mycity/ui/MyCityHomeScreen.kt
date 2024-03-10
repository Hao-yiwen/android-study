package com.example.mycity.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import com.example.mycity.R
import com.example.mycity.ui.utils.MyCityPageType

@Composable
fun MyCityScreen(
    myCityUiState: MyCityUiState,
    myCityViewModel: MyCityViewModel,
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier) {
        when (myCityUiState.currentShowPage) {
            MyCityPageType.FOOD_PAGE -> {
                FoodScreen(
                    myCityViewModel = myCityViewModel,
                    myCityUiState = myCityUiState,
                    modifier = Modifier
                        .padding(
                            top = dimensionResource(id = R.dimen.padding_large),
                        )
                        .padding(horizontal = dimensionResource(id = R.dimen.padding_large))
                )
            }

            MyCityPageType.PLACE_OF_FOOD_PAGE -> {
                PlaceOfFoodScreen(myCityUiState, modifier = modifier)
            }

            MyCityPageType.PLACE_DETAIL_PAGE -> {
                PlaceDetailScreen(modifier = modifier)
            }

            else -> {
                FoodScreen(
                    myCityViewModel = myCityViewModel,
                    myCityUiState = myCityUiState,
                    modifier = modifier
                )
            }
        }
    }
}