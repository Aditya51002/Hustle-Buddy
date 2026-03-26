package com.example.hustlebuddy.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Book
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.hustlebuddy.model.Subject
import com.example.hustlebuddy.ui.components.StudyBuddyButton
import com.example.hustlebuddy.ui.components.StudyBuddyTextField
import com.example.hustlebuddy.viewmodel.StudyBuddyViewModel
import java.util.UUID

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddSubjectScreen(navController: NavController, viewModel: StudyBuddyViewModel) {
    var name by remember { mutableStateOf("") }
    var selectedColor by remember { mutableStateOf(0xFF4285F4) }
    
    val colors = listOf(
        0xFF4285F4, 0xFFEA4335, 0xFF34A853, 0xFFFBBC05,
        0xFF673AB7, 0xFFE91E63, 0xFF00BCD4, 0xFFFF9800
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Add Subject") },
                navigationIcon = {
                    IconButton(onClick = { navController.navigateUp() }) {
                        Icon(Icons.Default.ArrowBack, "Back")
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            StudyBuddyTextField(
                value = name,
                onValueChange = { name = it },
                label = "Subject Name",
                leadingIcon = { Icon(Icons.Default.Book, null) }
            )

            Text("Choose Color", style = MaterialTheme.typography.titleMedium)
            
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(colors) { color ->
                    Box(
                        modifier = Modifier
                            .size(48.dp)
                            .clip(CircleShape)
                            .background(Color(color))
                            .clickable { selectedColor = color }
                            .padding(4.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        if (selectedColor == color) {
                            Box(
                                modifier = Modifier
                                    .size(16.dp)
                                    .clip(CircleShape)
                                    .background(Color.White)
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.weight(1f))

            StudyBuddyButton(
                text = "Save Subject",
                onClick = {
                    // TODO: Save to database here
                    if (name.isNotBlank()) {
                        viewModel.addSubject(
                            Subject(UUID.randomUUID().toString(), name, selectedColor.toLong(), "book", 0f)
                        )
                        navController.navigateUp()
                    }
                }
            )
        }
    }
}
