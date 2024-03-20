package com.example.bookshelf.state

import com.example.bookshelf.model.ImageLinks

sealed interface BookShelfType {
    object Loading : BookShelfType
    data class Success(val data: ImageLinks) : BookShelfType
    object Error : BookShelfType
}

data class BookShelfViewState(val ImageLinkList: List<BookShelfType>? = null)