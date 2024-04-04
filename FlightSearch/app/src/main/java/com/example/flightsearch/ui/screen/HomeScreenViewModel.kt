package com.example.flightsearch.ui.screen

import android.text.Spannable.Factory
import android.util.Log
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.toMutableStateList
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
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeScreenViewModel(
    val flightInputPreferencesRepository: FlightInputPreferencesRepository,
    val favoriteRepository: FavoriteRepository,
    val airportRepository: AirportRepository
) :
    ViewModel() {
    val _uiState = MutableStateFlow(HomeScreenUiState())

    val uiState: StateFlow<HomeScreenUiState> = _uiState.asStateFlow()


    init {
        processFlightInput()
        initDatabase()
    }

    fun initDatabase() {
        /**
         * @description 解决数据库初始化问题
         * - 在3月份使用数据库的时候发现，初次进入页面无法初始化数据库，经过debugger发现，问题是之前的airportRegistory方法是Flow类型，而Flow类型是冷流，
         * 只有订阅了才会执行，所以在初次进入页面的时候，没有订阅，导致数据库没有初始化，所以现在我改成Suspend,问题解决了
         * - 还有一个问题就是uistate的问题，uistate需要委托创建而不是初始化，例如
         * val uiState by viewModel.uiState.collectAsState()
         * @date: 2024/4/5 01:00 AM
         */
        viewModelScope.launch {
            airportRepository.searchAllAirportAsync()
        }
    }

    fun processFlightInput() {
        viewModelScope.launch {
            val searchStr = flightInputPreferencesRepository.searchStr.map {
                HomeScreenUiState(it)
            }.stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5000),
                initialValue = HomeScreenUiState()
            )
            _uiState.update {
                _uiState.value.copy(
                    searchStr = searchStr.value.searchStr
                )
            }
        }
    }

    fun searchAirport(str: String): Unit {
        viewModelScope.launch {
            val flightList: List<Airport> = airportRepository.searchAirportAsync("%" + str + "%")
            Log.d("HomeScreenViewModel", "searchAirport: ${flightList.toMutableStateList()}")
            _uiState.value = uiState.value.copy(searchResult = flightList.toMutableStateList())
        }
    }

    fun inputStrChangeHandle(str: String) {
        changeStr(str)
        viewModelScope.launch {
            searchAirport(str)
        }
    }


    fun changeStr(str: String) {
        _uiState.value = HomeScreenUiState(searchStr = str)
        viewModelScope.launch {
            flightInputPreferencesRepository.saveLayoutPreferences(str)
        }
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