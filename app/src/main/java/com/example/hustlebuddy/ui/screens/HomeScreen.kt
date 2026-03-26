package com.example.hustlebuddy.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.hustlebuddy.navigation.Screen
import com.example.hustlebuddy.ui.components.*
import com.example.hustlebuddy.viewmodel.StudyBuddyViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController, viewModel: StudyBuddyViewModel) {
    val user by viewModel.user.collectAsState()
    val tasks by viewModel.tasks.collectAsState()
    val stats by viewModel.stats.collectAsState()

    Scaffold(
        bottomBar = { BottomNavigationBar(navController) },
        floatingActionButton = {
            FloatingActionButton(onClick = { navController.navigate(Screen.AddTask.route) }) {
                Icon(Icons.Default.Add, "Add Task")
            }
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(horizontal = 20.dp)
        ) {
            item {
                Spacer(modifier = Modifier.height(24.dp))
                Text(
                    text = "Hello, ${user.name}! 👋",
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "What are we studying today?",
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.outline
                )
                Spacer(modifier = Modifier.height(24.dp))
            }

            item {
                StudyBuddyCard(
                    containerColor = MaterialTheme.colorScheme.primaryContainer
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column {
                            Text(
                                text = "Daily Progress",
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.Bold
                            )
                            Text(
                                text = "${stats.completedTasks} tasks completed today",
                                style = MaterialTheme.typography.bodyMedium
                            )
                        }
                        CircularProgressIndicator(
                            progress = { 0.75f },
                            modifier = Modifier.size(48.dp),
                            strokeWidth = 6.dp
                        )
                    }
                }
                Spacer(modifier = Modifier.height(24.dp))
            }

            item {
                SectionHeader(title = "Quick Actions")
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    QuickActionCard(
                        modifier = Modifier.weight(1f),
                        title = "Pomodoro",
                        icon = Icons.Default.Timer,
                        color = Color(0xFFFF8A65)
                    ) {
                        navController.navigate(Screen.Pomodoro.route)
                    }
                    QuickActionCard(
                        modifier = Modifier.weight(1f),
                        title = "Take Quiz",
                        icon = Icons.Default.Quiz,
                        color = Color(0xFF64B5F6)
                    ) {
                        navController.navigate(Screen.Quiz.route)
                    }
                }
                Spacer(modifier = Modifier.height(24.dp))
            }

            item {
                SectionHeader(
                    title = "Upcoming Tasks",
                    actionText = "See All",
                    onActionClick = { navController.navigate(Screen.Tasks.route) }
                )
            }

            items(tasks.take(3)) { task ->
                TaskListItem(task = task) {
                    viewModel.toggleTaskCompletion(task.id)
                }
                Spacer(modifier = Modifier.height(12.dp))
            }
            
            item { Spacer(modifier = Modifier.height(20.dp)) }
        }
    }
}

@Composable
fun QuickActionCard(
    title: String,
    icon: ImageVector,
    color: Color,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Card(
        onClick = onClick,
        modifier = modifier.height(100.dp),
        colors = CardDefaults.cardColors(containerColor = color.copy(alpha = 0.1f)),
        shape = MaterialTheme.shapes.medium
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(icon, contentDescription = null, tint = color)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = title, fontWeight = FontWeight.SemiBold, color = color)
        }
    }
}
