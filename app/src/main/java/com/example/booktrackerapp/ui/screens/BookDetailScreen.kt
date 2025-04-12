package com.example.booktrackerapp.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.booktrackerapp.data.local.BookEntity
import com.example.booktrackerapp.viewmodel.BookViewModel

@Composable
fun BookDetailScreen(
    book: BookEntity,
    viewModel: BookViewModel,
    onBack: (newStatus: String) -> Unit
) {
    var progress by remember { mutableStateOf(book.currentPage.toString()) }
    var rating by remember { mutableStateOf(book.rating?.toString() ?: "") }
    var review by remember { mutableStateOf(book.review ?: "") }
    var status by remember { mutableStateOf(book.status) }

    Column(modifier = Modifier.padding(16.dp)) {
        Text("Book Details", style = MaterialTheme.typography.headlineSmall)

        Spacer(modifier = Modifier.height(10.dp))

        Row(verticalAlignment = Alignment.CenterVertically) {
            book.imageUrl?.let {
                Image(
                    painter = rememberAsyncImagePainter(it),
                    contentDescription = book.title,
                    modifier = Modifier.size(80.dp)
                )
            }
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text(book.title, style = MaterialTheme.typography.titleMedium)
                Text("by ${book.author}", style = MaterialTheme.typography.bodyMedium)
                Text("Pages: ${book.pageCount}", style = MaterialTheme.typography.bodySmall)
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = progress,
            onValueChange = { progress = it },
            label = { Text("Current Page") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = rating,
            onValueChange = { rating = it },
            label = { Text("Rating (1-5)") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = review,
            onValueChange = { review = it },
            label = { Text("Review") },
            modifier = Modifier.fillMaxWidth(),
            maxLines = 4
        )

        Spacer(modifier = Modifier.height(10.dp))

        Row {
            Button(
                onClick = { status = "READING" },
                modifier = Modifier.weight(1f)
            ) {
                Text("Mark as Reading")
            }

            Spacer(modifier = Modifier.width(8.dp))

            Button(
                onClick = { status = "COMPLETED" },
                modifier = Modifier.weight(1f)
            ) {
                Text("Mark as Completed")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            val updatedBook = book.copy(
                currentPage = progress.toIntOrNull() ?: 0,
                rating = rating.toIntOrNull(),
                review = review,
                status = status
            )
            viewModel.updateBook(updatedBook)
            onBack(status) // Pass updated status
        }) {
            Text("Save Changes")
        }
    }
}
