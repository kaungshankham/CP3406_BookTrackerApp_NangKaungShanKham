package com.example.booktrackerapp.data.remote

import retrofit2.http.GET
import retrofit2.http.Query

data class GoogleBooksResponse(
    val items: List<BookItem>?
)

data class BookItem(
    val id: String,
    val volumeInfo: VolumeInfo
)

data class VolumeInfo(
    val title: String,
    val authors: List<String>?,
    val pageCount: Int?,
    val imageLinks: ImageLinks?
)

data class ImageLinks(
    val thumbnail: String?
)

interface GoogleBooksService {
    @GET("volumes")
    suspend fun searchBooks(@Query("q") query: String): GoogleBooksResponse
}
