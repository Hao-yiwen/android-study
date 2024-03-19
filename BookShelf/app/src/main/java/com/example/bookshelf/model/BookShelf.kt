package com.example.bookshelf.model

data class BookShelf(val kind: String, val totalItems: Int, val items: List<Book>)

data class Book(
    val kind: String,
    val id: String,
    val etag: String,
    val selfLink: String,
    val volumeInfo: VolumeInfo
)

data class VolumeInfo(
    val title: String,
    val authors: List<String>,
    val publisher: String,
    val publishedDate: String,
    val description: String,
    val pageCount: Int,
    val printType: String,
    val categories: List<String>,
    val averageRating: Double,
    val ratingsCount: Int,
    val maturityRating: String,
    val allowAnonLogging: Boolean,
    val contentVersion: String,
    val imageLinks: ImageLinks,
    val language: String,
    val previewLink: String,
    val infoLink: String,
    val canonicalVolumeLink: String
)

data class ImageLinks(
    val smallThumbnail: String,
    val thumbnail: String,
    val small: String,
    val medium: String,
)