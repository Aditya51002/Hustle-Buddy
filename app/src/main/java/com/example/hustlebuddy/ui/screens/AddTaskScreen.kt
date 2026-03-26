package com.example.hustlebuddy.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.hustlebuddy.model.Priority
import com.example.hustlebuddy.model.Task
import com.example.hustlebuddy.ui.components.StudyBuddyButton
import com.example.hustlebuddy.ui.components.StudyBuddyTextField
import com.example.hustlebuddy.viewmodel.StudyBuddyViewModel
import java.util.UUID

@OptIn(Material3Api::class)
@Composable
fun AddTaskScreen(navController: NavController, viewModel: StudyBuddyViewModel) {
    var title by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var deadline by remember { mutableStateOf("") }
    var priority by remember { mutableStateOf(Priority.MEDIUM) }
    
    val subjects by viewModel.subjects.collectAsState()
    var selectedSubjectId by remember { mutableStateOf(subjects.firstOrNull()?.id ?: "") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Add Task") },
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
            StudyBuddyTextField(value = title, onValueChange = { title = it }, label = "Task Title")
            StudyBuddyTextField(value = description, onValueChange = { description = it }, label = "Description")
            StudyBuddyTextField(value = deadline, onValueChange = { deadline = it }, label = "Deadline (e.g., 2023-11-20)")

            Text("Priority", style = MaterialTheme.typography.titleMedium)
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                Priority.values().forEach { p ->
                    FilterChip(
                        selected = priority == p,
                        onClick = { priority = p },
                        label = { Text(p.name) }
                    )
                }
            }

            Spacer(modifier = Modifier.weight(1f))

            StudyBuddyButton(
                text = "Save Task",
                onClick = {
                    // TODO: Connect task persistence/backend logic here
                    if (title.isNotBlank()) {
                        viewModel.addTask(
                            Task(
                                UUID.randomUUID().toString(),
                                title,
                                description,
                                selectedSubjectId,
                                deadline,
                                priority
                            )
                        )
                        navController.navigateUp()
                    }
                }
            )
        }
    }
}
