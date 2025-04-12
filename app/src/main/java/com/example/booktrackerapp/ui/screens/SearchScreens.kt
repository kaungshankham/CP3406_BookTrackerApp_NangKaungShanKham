package com.example.booktrackerapp.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.booktrackerapp.data.local.BookEntity
import com.example.booktrackerapp.data.remote.BookItem
import com.example.booktrackerapp.viewmodel.BookViewModel

@Composable
fun SearchScreen(
    viewModel: BookViewModel,
    onNavigateToLibrary: () -> Unit
) {
    var query by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Search for Books",
            style = MaterialTheme.typography.headlineSmall
        )

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
            value = query,
            onValueChange = { query = it },
            label = { Text("Search by title or author") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            Button(
                onClick = { viewModel.searchBooks(query) },
                modifier = Modifier.weight(1f)
            ) {
                Text("Search")
            }

            Spacer(modifier = Modifier.width(8.dp))

            OutlinedButton(
                onClick = { onNavigateToLibrary() },
                modifier = Modifier.weight(1f)
            ) {
                Text("Go to Library")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        BookSearchResults(
            results = viewModel.searchResults.collectAsState().value,
            onAddClicked = { item ->
                val entity = BookEntity(
                    id = item.id,
                    title = item.volumeInfo.title,
                    author = item.volumeInfo.authors?.joinToString(", ") ?: "Unknown",
                    pageCount = item.volumeInfo.pageCount ?: 0,
                    imageUrl = item.volumeInfo.imageLinks?.thumbnail,
                    status = "TO_READ",
                    currentPage = 0,
                    rating = null,
                    review = null
                )
                viewModel.addBook(entity)
            }
        )
    }
}

@Composable
fun BookSearchResults(results: List<BookItem>, onAddClicked: (BookItem) -> Unit) {
    if (results.isEmpty()) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(32.dp),
            contentAlignment = Alignment.Center
        ) {
            Text("No results found. Try searching something else.")
        }
    } else {
        LazyColumn {
            items(results) { item ->
                BookSearchItem(item = item, onAddClicked = onAddClicked)
            }
        }
    }
}

@Composable
fun BookSearchItem(item: BookItem, onAddClicked: (BookItem) -> Unit) {
    Card(
        modifier = Modifier
            .padding(vertical = 6.dp)
            .fillMaxWidth(),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Row(modifier = Modifier.padding(10.dp)) {
            val imageUrl = item.volumeInfo.imageLinks?.thumbnail
            if (imageUrl != null) {
                Image(
                    painter = rememberAsyncImagePainter(imageUrl),
                    contentDescription = item.volumeInfo.title,
                    modifier = Modifier.size(64.dp)
                )
            }

            Spacer(modifier = Modifier.width(10.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    item.volumeInfo.title,
                    style = MaterialTheme.typography.titleMedium
                )
                Text(
                    text = item.volumeInfo.authors?.joinToString(", ") ?: "Unknown Author",
                    style = MaterialTheme.typography.bodySmall
                )
            }

            Spacer(modifier = Modifier.width(8.dp))

            Button(onClick = { onAddClicked(item) }) {
                Text("Add")
            }
        }
    }
}
