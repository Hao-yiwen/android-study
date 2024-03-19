package com.example.bookshelf.state

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow

class BookShelfViewModel : ViewModel() {
    val _uiState = MutableStateFlow(BookShelfUiState())
    val uiState = _uiState

    init {
        getBookShelf()
    }

    fun getBookShelf(){

    }
}