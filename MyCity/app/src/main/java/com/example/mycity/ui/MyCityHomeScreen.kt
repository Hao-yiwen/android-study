package com.example.mycity.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import com.example.mycity.R
import com.example.mycity.ui.utils.MyCityContentType
import com.example.mycity.ui.utils.MyCityPageType

@Composable
fun MyCityScreen(
    contentType: MyCityContentType,
    myCityUiState: MyCityUiState,
    myCityViewModel: MyCityViewModel,
    modifier: Modifier = Modifier
) {
    if (contentType == MyCityContentType.LIST_AND_DETAIL) {
        Row(modifier = Modifier.fillMaxSize()) {
            FoodScreen(
                myCityViewModel = myCityViewModel,
                myCityUiState = myCityUiState,
                modifier = modifier
                    .weight(1f)
                    .padding(
                        top = dimensionResource(id = R.dimen.padding_large),
                    )
                    .padding(horizontal = dimensionResource(id = R.dimen.padding_large))
            )
            PlaceOfFoodScreen(
                myCityViewModel, myCityUiState, modifier = modifier
                    .weight(1f)
                    .padding(
                        top = dimensionResource(id = R.dimen.padding_large),
                    )
                    .padding(horizontal = dimensionResource(id = R.dimen.padding_large))
            )
            PlaceDetailScreen(
                myCityViewModel, myCityUiState,
                modifier = modifier
                    .weight(1f)
                    .padding(
                        top = dimensionResource(id = R.dimen.padding_large),
                    )
                    .padding(horizontal = dimensionResource(id = R.dimen.padding_large))
            )
        }
    } else {
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
                    PlaceOfFoodScreen(
                        myCityViewModel, myCityUiState, modifier = Modifier
                            .padding(
                                top = dimensionResource(id = R.dimen.padding_large),
                            )
                            .padding(horizontal = dimensionResource(id = R.dimen.padding_large))
                    )
                }

                MyCityPageType.PLACE_DETAIL_PAGE -> {
                    PlaceDetailScreen(
                        myCityViewModel, myCityUiState,
                        modifier = Modifier
                            .padding(
                                top = dimensionResource(id = R.dimen.padding_large),
                            )
                            .padding(horizontal = dimensionResource(id = R.dimen.padding_large))
                    )
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

}