package com.example.booktrackerapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.booktrackerapp.data.local.BookEntity
import com.example.booktrackerapp.data.remote.BookItem
import com.example.booktrackerapp.repository.BookRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class BookViewModel(private val repository: BookRepository) : ViewModel() {

    private val _searchResults = MutableStateFlow<List<BookItem>>(emptyList())
    val searchResults = _searchResults.asStateFlow()

    fun searchBooks(query: String) {
        viewModelScope.launch {
            try {
                println("Searching for: $query")
                val result = repository.searchBooks(query)
                _searchResults.value = result.items ?: emptyList()
                println("Books found: ${result.items?.size}")
            } catch (e: Exception) {
                println("Search error: ${e.message}")
                e.printStackTrace()
            }
        }
    }

    fun addBook(book: BookEntity) {
        viewModelScope.launch {
            repository.insertBook(book)
        }
    }

    fun updateBook(book: BookEntity) {
        viewModelScope.launch {
            repository.updateBook(book)
        }
    }

    fun getBooksByStatus(status: String) = repository.getBooksByStatus(status)
    fun getAllBooks() = repository.getAllBooks() // ✅ New method to fetch all books
    fun getBookById(id: String): Flow<BookEntity> = repository.getBookById(id)
}
