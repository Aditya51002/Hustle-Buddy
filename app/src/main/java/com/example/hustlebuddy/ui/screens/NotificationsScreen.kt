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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.hustlebuddy.ui.components.SectionHeader
import com.example.hustlebuddy.ui.components.StudyBuddyCard

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NotificationsScreen(navController: NavController) {
    // TODO: Connect WorkManager / backend
    var studyRemindersEnabled by remember { mutableStateOf(true) }
    var taskDeadlinesEnabled by remember { mutableStateOf(true) }

    val notifications = remember {
        listOf(
            "Time for Mathematics study session!",
            "Task 'Calculus Assignment' is due tomorrow",
            "You've reached a 7-day streak! Keep going!",
            "New Quiz available: Data Structures"
        )
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Notifications & Reminders") },
                navigationIcon = {
                    IconButton(onClick = { navController.navigateUp() }) {
                        Icon(Icons.Default.ArrowBack, "Back")
                    }
                }
            )
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(horizontal = 20.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = PaddingValues(top = 16.dp, bottom = 16.dp)
        ) {
            item {
                SectionHeader(title = "Smart Suggestions")
                StudyBuddyCard(containerColor = MaterialTheme.colorScheme.secondaryContainer.copy(alpha = 0.4f)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Default.Lightbulb, null, tint = MaterialTheme.colorScheme.secondary)
                        Spacer(modifier = Modifier.width(16.dp))
                        Column {
                            Text(text = "Better Study Time", fontWeight = FontWeight.Bold)
                            Text(text = "We noticed you're more focused at 8 PM. Want a reminder then?", style = MaterialTheme.typography.bodySmall)
                            Button(
                                onClick = { /* TODO: Set smart reminder */ },
                                modifier = Modifier.padding(top = 8.dp),
                                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.secondary)
                            ) {
                                Text("Schedule it")
                            }
                        }
                    }
                }
            }

            item {
                SectionHeader(title = "Preferences")
                StudyBuddyCard {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text("Study Session Reminders")
                        Switch(checked = studyRemindersEnabled, onCheckedChange = { studyRemindersEnabled = it })
                    }
                    Divider(modifier = Modifier.padding(vertical = 8.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text("Task Deadline Alerts")
                        Switch(checked = taskDeadlinesEnabled, onCheckedChange = { taskDeadlinesEnabled = it })
                    }
                }
            }

            item {
                SectionHeader(title = "Recent Notifications")
            }

            items(notifications) { notification ->
                NotificationItem(notification)
            }
        }
    }
}

@Composable
fun NotificationItem(text: String) {
    StudyBuddyCard(containerColor = MaterialTheme.colorScheme.surface) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                Icons.Default.NotificationsActive,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier.size(24.dp)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Text(text = text, style = MaterialTheme.typography.bodyMedium)
        }
    }
}
