package com.example.booktrackerapp.repository

import com.example.booktrackerapp.data.local.BookDao
import com.example.booktrackerapp.data.local.BookEntity
import com.example.booktrackerapp.data.remote.RetrofitInstance

class BookRepository(private val bookDao: BookDao) {

    // ROOM
    suspend fun insertBook(book: BookEntity) = bookDao.insertBook(book)
    suspend fun updateBook(book: BookEntity) = bookDao.updateBook(book)
    fun getAllBooks() = bookDao.getAllBooks()
    fun getBooksByStatus(status: String) = bookDao.getBooksByStatus(status)
    fun getBookById(id: String) = bookDao.getBookById(id)

    // RETROFIT
    suspend fun searchBooks(query: String) =
        RetrofitInstance.api.searchBooks(query)
}
