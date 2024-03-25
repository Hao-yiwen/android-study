package com.example.flightsearch.ui.screen

import android.text.Spannable.Factory
import android.util.Log
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.flightsearch.FlightSearchApplication
import com.example.flightsearch.data.Airport
import com.example.flightsearch.data.AirportRepository
import com.example.flightsearch.data.FavoriteRepository
import com.example.flightsearch.data.FlightInputPreferencesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class HomeScreenViewModel(
    val flightInputPreferencesRepository: FlightInputPreferencesRepository,
    val favoriteRepository: FavoriteRepository,
    val airportRepository: AirportRepository
) :
    ViewModel() {
    val _uiState = MutableStateFlow(HomeScreenUiState())

    val uiState: StateFlow<HomeScreenUiState> = flightInputPreferencesRepository.searchStr.map {
        HomeScreenUiState(it)
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = HomeScreenUiState()
    )

    init {
        viewModelScope.launch {
            airportRepository.searchAllAirport()
        }
    }

    fun searchAirport(str: String): Unit {
        viewModelScope.launch {
            val flightList: Flow<List<Airport>> = airportRepository.searchAirport("%" + str + "%")
            val uiStateFlow: StateFlow<HomeScreenUiState> = flightList
                .map { flightList ->
                    HomeScreenUiState(
                        searchResult = flightList // 假设你的 HomeScreenUiState 支持列表
                    )
                }
                .stateIn(
                    scope = viewModelScope,
                    started = SharingStarted.WhileSubscribed(5000),
                    initialValue = HomeScreenUiState()
                )
            Log.d("HomeScreenViewModel", "searchAirport: ${uiStateFlow.value.searchResult}")
            _uiState.value = uiState.value.copy(searchResult = uiStateFlow.value.searchResult)
        }
    }


    fun changeStr(str: String) {
        viewModelScope.launch {
            flightInputPreferencesRepository.saveLayoutPreferences(str)
        }
        _uiState.value = HomeScreenUiState(searchStr = str)
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as FlightSearchApplication)
                HomeScreenViewModel(
                    flightInputPreferencesRepository = application.container.flightInputPreferencesRepository,
                    airportRepository = application.container.airportRepositoryProvider,
                    favoriteRepository = application.container.favoriteRepositoryProvider
                )
            }
        }
    }
}

data class HomeScreenUiState(
    val searchStr: String = "",
    val searchResult: List<Airport> = emptyList()
)