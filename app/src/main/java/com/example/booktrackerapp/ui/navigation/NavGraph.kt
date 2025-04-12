package com.example.booktrackerapp.ui.navigation

import androidx.compose.runtime.*
import androidx.navigation.NavType
import androidx.navigation.compose.*
import androidx.navigation.navArgument
import com.example.booktrackerapp.viewmodel.BookViewModel
import com.example.booktrackerapp.ui.screens.*

sealed class Screen(val route: String) {
    object Search : Screen("search")
    object Library : Screen("library")
    object BookDetail : Screen("book_detail/{bookId}") {
        fun createRoute(bookId: String) = "book_detail/$bookId"
    }
}

@Composable
fun NavGraph(viewModel: BookViewModel) {
    val navController = rememberNavController()

    NavHost(navController, startDestination = Screen.Search.route) {

        // Search Screen
        composable(Screen.Search.route) {
            SearchScreen(
                viewModel = viewModel,
                onNavigateToLibrary = {
                    navController.navigate(Screen.Library.route)
                }
            )
        }

        // 📚 Library Screen (no status argument)
        composable(Screen.Library.route) {
            LibraryScreen(
                viewModel = viewModel,
                onBookClick = { book ->
                    navController.navigate(Screen.BookDetail.createRoute(book.id))
                }
            )
        }

        // 📖 Book Detail Screen
        composable(
            route = Screen.BookDetail.route,
            arguments = listOf(navArgument("bookId") { type = NavType.StringType })
        ) { backStackEntry ->
            val bookId = backStackEntry.arguments?.getString("bookId") ?: return@composable
            val book by viewModel.getBookById(bookId).collectAsState(initial = null)

            if (book != null) {
                BookDetailScreen(
                    book = book!!,
                    viewModel = viewModel,
                    onBack = { navController.popBackStack() }
                )
            }
        }
    }
}
