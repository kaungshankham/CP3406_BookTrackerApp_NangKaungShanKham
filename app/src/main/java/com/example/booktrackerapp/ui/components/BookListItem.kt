package com.example.booktrackerapp.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.booktrackerapp.data.local.BookEntity

@Composable
fun BookListItem(book: BookEntity, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .padding(vertical = 6.dp)
            .fillMaxWidth()
            .clickable { onClick() },
        elevation = CardDefaults.cardElevation(3.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(10.dp)
        ) {
            book.imageUrl?.let {
                Image(
                    painter = rememberAsyncImagePainter(it),
                    contentDescription = book.title,
                    modifier = Modifier.size(60.dp)
                )
            }

            Spacer(modifier = Modifier.width(10.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text(text = book.title, style = MaterialTheme.typography.titleMedium)
                Text(text = "by ${book.author}", style = MaterialTheme.typography.bodySmall)
            }

            Spacer(modifier = Modifier.width(10.dp))

            AssistChip(
                onClick = {},
                label = {
                    Text(
                        when (book.status) {
                            "TO_READ" -> "To Read"
                            "READING" -> "Reading"
                            "COMPLETED" -> "Completed"
                            else -> book.status
                        }
                    )
                }
            )
        }
    }
}

