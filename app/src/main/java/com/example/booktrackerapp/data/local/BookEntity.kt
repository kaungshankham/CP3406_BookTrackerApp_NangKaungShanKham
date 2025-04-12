package com.example.booktrackerapp.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "books")
data class BookEntity(
    @PrimaryKey val id: String,
    val title: String,
    val author: String,
    val pageCount: Int,
    val imageUrl: String?,
    val status: String,
    val currentPage: Int = 0,
    val rating: Int? = null,
    val review: String? = null
)
