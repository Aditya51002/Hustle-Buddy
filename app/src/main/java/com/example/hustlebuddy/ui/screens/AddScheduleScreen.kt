package com.example.hustlebuddy.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.hustlebuddy.model.Schedule
import com.example.hustlebuddy.ui.components.StudyBuddyButton
import com.example.hustlebuddy.ui.components.StudyBuddyTextField
import com.example.hustlebuddy.viewmodel.StudyBuddyViewModel
import java.util.UUID

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddScheduleScreen(navController: NavController, viewModel: StudyBuddyViewModel) {
    var date by remember { mutableStateOf("") }
    var startTime by remember { mutableStateOf("") }
    var duration by remember { mutableStateOf("") }
    var notes by remember { mutableStateOf("") }

    val subjects by viewModel.subjects.collectAsState()
    var selectedSubjectId by remember { mutableStateOf(subjects.firstOrNull()?.id ?: "") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Add Schedule") },
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
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text("Select Subject", style = MaterialTheme.typography.titleMedium)
            ScrollableTabRow(
                selectedTabIndex = subjects.indexOfFirst { it.id == selectedSubjectId }.coerceAtLeast(0),
                edgePadding = 0.dp,
                divider = {}
            ) {
                subjects.forEach { subject ->
                    Tab(
                        selected = selectedSubjectId == subject.id,
                        onClick = { selectedSubjectId = subject.id }
                    ) {
                        Text(subject.name, modifier = Modifier.padding(16.dp))
                    }
                }
            }

            StudyBuddyTextField(value = date, onValueChange = { date = it }, label = "Date (e.g., Monday)")
            StudyBuddyTextField(value = startTime, onValueChange = { startTime = it }, label = "Start Time")
            StudyBuddyTextField(value = duration, onValueChange = { duration = it }, label = "Duration")
            StudyBuddyTextField(value = notes, onValueChange = { notes = it }, label = "Notes")

            Spacer(modifier = Modifier.weight(1f))

            StudyBuddyButton(
                text = "Save Schedule",
                onClick = {
                    // TODO: Add comment where backend/database connection will be added later
                    if (selectedSubjectId.isNotBlank()) {
                        viewModel.addSchedule(
                            Schedule(UUID.randomUUID().toString(), selectedSubjectId, date, startTime, duration, notes)
                        )
                        navController.navigateUp()
                    }
                }
            )
        }
    }
}
