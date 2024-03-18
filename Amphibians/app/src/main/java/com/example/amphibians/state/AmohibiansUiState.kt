package com.example.amphibians.state

import com.example.amphibians.model.Amohibians

sealed interface AmohibiansListType {
    data class Success(val amohibiansList: List<Amohibians>) : AmohibiansListType
    object Error : AmohibiansListType
    object Loading : AmohibiansListType
}

data class AmohibiansUiState(
    val amohibiansList: AmohibiansListType = AmohibiansListType.Loading,
)