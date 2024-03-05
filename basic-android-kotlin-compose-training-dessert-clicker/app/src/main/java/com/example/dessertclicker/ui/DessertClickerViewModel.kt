package com.example.dessertclicker.ui

import androidx.lifecycle.ViewModel
import com.example.dessertclicker.data.Datasource
import com.example.dessertclicker.data.DessertClickerUiState
import com.example.dessertclicker.determineDessertToShow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class DessertClickerViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(DessertClickerUiState())

    val uiState: StateFlow<DessertClickerUiState> = _uiState.asStateFlow()

    fun updateRevenue(currentDessertPrice: Int) {
        val dessertToShow =
            determineDessertToShow(Datasource.dessertList, _uiState.value.dessertsSold)
        _uiState.update { currentState ->
            currentState.copy(
                revenue = currentState.revenue + currentDessertPrice,
                dessertsSold = currentState.dessertsSold + 1,
                currentDessertPrice = dessertToShow.price,
                currentDessertImageId = dessertToShow.imageId
            )
        }
    }
}