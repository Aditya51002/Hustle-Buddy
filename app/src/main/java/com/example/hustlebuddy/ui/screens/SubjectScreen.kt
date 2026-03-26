package com.example.hustlebuddy.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Book
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.hustlebuddy.model.Subject
import com.example.hustlebuddy.navigation.Screen
import com.example.hustlebuddy.viewmodel.StudyBuddyViewModel

@OptIn(Material3Api::class)
@Composable
fun SubjectScreen(navController: NavController, viewModel: StudyBuddyViewModel) {
    val subjects by viewModel.subjects.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("My Subjects") })
        },
        bottomBar = { BottomNavigationBar(navController) },
        floatingActionButton = {
            FloatingActionButton(onClick = { navController.navigate(Screen.AddSubject.route) }) {
                Icon(Icons.Default.Add, "Add Subject")
            }
        }
    ) { padding ->
        // TODO: Connect data fetch from backend/database here
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(16.dp),
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(subjects) { subject ->
                SubjectCard(subject = subject)
            }
        }
    }
}

@Composable
fun SubjectCard(subject: Subject) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(180.dp),
        shape = MaterialTheme.shapes.large,
        colors = CardDefaults.cardColors(
            containerColor = Color(subject.color).copy(alpha = 0.1f)
        )
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.Book,
                    contentDescription = null,
                    tint = Color(subject.color)
                )
                Row {
                    IconButton(onClick = { /* TODO: Edit Subject */ }) {
                        Icon(Icons.Default.Edit, "Edit", tint = Color.Gray, modifier = Modifier.size(18.dp))
                    }
                    IconButton(onClick = { /* TODO: Delete Subject */ }) {
                        Icon(Icons.Default.Delete, "Delete", tint = Color.Gray, modifier = Modifier.size(18.dp))
                    }
                }
            }
            
            Column {
                Text(
                    text = subject.name,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    color = Color(subject.color)
                )
                Spacer(modifier = Modifier.height(8.dp))
                LinearProgressIndicator(
                    progress = subject.progress,
                    modifier = Modifier.fillMaxWidth(),
                    color = Color(subject.color),
                    trackColor = Color(subject.color).copy(alpha = 0.2f)
                )
                Text(
                    text = "${(subject.progress * 100).toInt()}% Completed",
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.Gray
                )
            }
        }
    }
}
