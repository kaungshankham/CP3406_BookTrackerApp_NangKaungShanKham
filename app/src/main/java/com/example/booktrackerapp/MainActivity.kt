package com.example.booktrackerapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.booktrackerapp.data.local.BookDatabase
import com.example.booktrackerapp.repository.BookRepository
import com.example.booktrackerapp.ui.navigation.NavGraph
import com.example.booktrackerapp.ui.theme.BookTrackerAppTheme
import com.example.booktrackerapp.viewmodel.BookViewModel

class MainActivity : ComponentActivity() {

    private lateinit var viewModel: BookViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // Set up Room DAO and repository
        val dao = BookDatabase.getDatabase(applicationContext).bookDao()
        val repository = BookRepository(dao)

        // Manually inject ViewModel (replaceable with Hilt or SavedStateHandle later)
        viewModel = ViewModelProvider(this, object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                if (modelClass.isAssignableFrom(BookViewModel::class.java)) {
                    return BookViewModel(repository) as T
                }
                throw IllegalArgumentException("Unknown ViewModel class")
            }
        })[BookViewModel::class.java]

        // Compose UI
        setContent {
            BookTrackerAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) {
                    NavGraph(viewModel = viewModel)
                }
            }
        }
    }
}
