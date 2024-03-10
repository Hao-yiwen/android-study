package com.example.mycity.ui

import com.example.mycity.model.Food
import com.example.mycity.model.Place
import com.example.mycity.ui.utils.MyCityPageType

data class MyCityUiState(
    val currentFood: Food? = null,
    val currentPlace: Place? = null,
    val foodPlaces: Map<Long, List<Place>> = emptyMap(),
    val currentShowPage: MyCityPageType = MyCityPageType.FOOD_PAGE
) {
    val currentFoodPlaces: List<Place> by lazy {
        foodPlaces.getValue(foodPlaces.keys.first { it == currentFood?.id })
    }
}