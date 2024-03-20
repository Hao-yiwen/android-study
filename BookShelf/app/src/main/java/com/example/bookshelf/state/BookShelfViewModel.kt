package com.example.bookshelf.state

import android.text.Spannable.Factory
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.bookshelf.BookShelfApplication
import com.example.bookshelf.data.BookShelfRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class BookShelfViewModel(val bookShelfRepository: BookShelfRepository) : ViewModel() {
    private val _uiState = MutableStateFlow(BookShelfViewState())
    val uiState = _uiState

    init {
        getBookShelf()
    }

    private fun getBookShelf() {
        viewModelScope.launch {
            val res = bookShelfRepository.getBookShelf("jazz+history")
            Log.d("BookShelfViewModel", "getBookShelf: $res")
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as BookShelfApplication)
                val repository = application.appContainer.bookShelfRepository
                BookShelfViewModel(repository)
            }
        }
    }
}