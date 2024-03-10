package com.example.mycity.ui

import androidx.lifecycle.ViewModel
import com.example.mycity.data.FoodDataProvider
import com.example.mycity.data.PlaceDataProvider
import com.example.mycity.model.Food
import com.example.mycity.model.Place
import com.example.mycity.ui.utils.MyCityPageType
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class MyCityViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(MyCityUiState())
    val uiState: StateFlow<MyCityUiState> = _uiState

    init {
        initMyCityUiState()
    }

    fun initMyCityUiState() {
        val placesOfFood: Map<Long, List<Place>> =
            PlaceDataProvider.defaultPlace.groupBy { it.foodId }
        _uiState.value = MyCityUiState(
            foodPlaces = placesOfFood
        )
    }

    fun updateCurrentFood(food: Food) {
        _uiState.value = _uiState.value.copy(
            currentFood = food,
            currentShowPage = MyCityPageType.PLACE_OF_FOOD_PAGE
        )
    }
}