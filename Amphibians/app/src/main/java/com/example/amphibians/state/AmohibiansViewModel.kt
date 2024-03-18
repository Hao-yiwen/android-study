package com.example.amphibians.state

import android.text.Spannable.Factory
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.amphibians.AmohibiansApplication
import com.example.amphibians.data.AmohibiansRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class AmohibiansViewModel(private val amohibiansRepository: AmohibiansRepository) : ViewModel() {
    private val _uistate = MutableStateFlow(AmohibiansUiState())
    val uiState = _uistate

    init {
        getAmphibiansList()
    }

    fun getAmphibiansList() {
        viewModelScope.launch {
            uiState.value = AmohibiansUiState(AmohibiansListType.Loading)
            uiState.value = try {
                val result = amohibiansRepository.getAmohibians()
                AmohibiansUiState(AmohibiansListType.Success(result))
            } catch (e: Exception) {
                AmohibiansUiState(AmohibiansListType.Error)
            }
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as AmohibiansApplication)
                val repository = application.container.amphibiansRepository
                AmohibiansViewModel(repository)
            }
        }
    }
}