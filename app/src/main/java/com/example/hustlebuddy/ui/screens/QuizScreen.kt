package com.example.hustlebuddy.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Quiz
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.hustlebuddy.model.Quiz
import com.example.hustlebuddy.navigation.Screen
import com.example.hustlebuddy.viewmodel.StudyBuddyViewModel

@OptIn(Material3Api::class)
@Composable
fun QuizScreen(navController: NavController, viewModel: StudyBuddyViewModel) {
    val quizzes by viewModel.quizzes.collectAsState()

    Scaffold(
        topBar = { TopAppBar(title = { Text("Quizzes") }) },
        bottomBar = { BottomNavigationBar(navController) }
    ) { padding ->
        LazyVerticalGrid(
            columns = GridCells.Fixed(1),
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(quizzes) { quiz ->
                QuizCard(quiz) {
                    navController.navigate(Screen.QuizAttempt.createRoute(quiz.id))
                }
            }
        }
    }
}

@Composable
fun QuizCard(quiz: Quiz, onStart: () -> Unit) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = MaterialTheme.shapes.large
    ) {
        Row(
            modifier = Modifier
                .padding(20.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Text(text = quiz.title, style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
                Text(text = quiz.category, style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.outline)
                Text(text = "${quiz.questions.size} Questions", style = MaterialTheme.typography.bodySmall)
                Spacer(modifier = Modifier.height(16.dp))
                Button(onClick = onStart) {
                    Text("Start Quiz")
                }
            }
            Icon(
                Icons.Default.Quiz,
                contentDescription = null,
                modifier = Modifier.size(48.dp),
                tint = MaterialTheme.colorScheme.primary
            )
        }
    }
}
