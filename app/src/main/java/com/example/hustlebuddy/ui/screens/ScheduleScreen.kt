package com.example.hustlebuddy.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.hustlebuddy.model.Schedule
import com.example.hustlebuddy.navigation.Screen
import com.example.hustlebuddy.ui.components.StudyBuddyCard
import com.example.hustlebuddy.viewmodel.StudyBuddyViewModel

@OptIn(Material3Api::class)
@Composable
fun ScheduleScreen(navController: NavController, viewModel: StudyBuddyViewModel) {
    val schedules by viewModel.schedules.collectAsState()
    val subjects by viewModel.subjects.collectAsState()

    Scaffold(
        topBar = { TopAppBar(title = { Text("Study Schedule") }) },
        floatingActionButton = {
            FloatingActionButton(onClick = { navController.navigate(Screen.AddSchedule.route) }) {
                Icon(Icons.Default.Add, "Add Schedule")
            }
        },
        bottomBar = { BottomNavigationBar(navController) }
    ) { padding ->
        Column(modifier = Modifier.padding(padding)) {
            // TODO: Connect calendar-style picker or weekly selector logic
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(schedules) { schedule ->
                    val subject = subjects.find { it.id == schedule.subjectId }
                    ScheduleItem(schedule, subject?.name ?: "Unknown Subject", subject?.color ?: 0xFF666666)
                }
            }
        }
    }
}

@Composable
fun ScheduleItem(schedule: Schedule, subjectName: String, color: Long) {
    StudyBuddyCard(containerColor = MaterialTheme.colorScheme.surface) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(
                    text = subjectName,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    color = Color(color)
                )
                Text(text = "${schedule.date} • ${schedule.startTime}", style = MaterialTheme.typography.bodyMedium)
                Text(text = "Duration: ${schedule.duration}", style = MaterialTheme.typography.bodySmall, color = Color.Gray)
            }
            Icon(Icons.Default.CalendarToday, contentDescription = null, tint = Color(color).copy(alpha = 0.5f))
        }
    }
}
