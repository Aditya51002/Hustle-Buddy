package com.example.hustlebuddy.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.FilterList
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.hustlebuddy.navigation.Screen
import com.example.hustlebuddy.viewmodel.StudyBuddyViewModel

@OptIn(Material3Api::class)
@Composable
fun TaskScreen(navController: NavController, viewModel: StudyBuddyViewModel) {
    val tasks by viewModel.tasks.collectAsState()
    var selectedFilter by remember { mutableStateOf("All") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Tasks") },
                actions = {
                    IconButton(onClick = { /* TODO: Show filter dialog */ }) {
                        Icon(Icons.Default.FilterList, contentDescription = "Filter")
                    }
                }
            )
        },
        bottomBar = { BottomNavigationBar(navController) },
        floatingActionButton = {
            FloatingActionButton(onClick = { navController.navigate(Screen.AddTask.route) }) {
                Icon(Icons.Default.Add, "Add Task")
            }
        }
    ) { padding ->
        Column(modifier = Modifier.padding(padding)) {
            ScrollableTabRow(
                selectedTabIndex = if (selectedFilter == "All") 0 else 1,
                edgePadding = 16.dp,
                divider = {}
            ) {
                Tab(selected = selectedFilter == "All", onClick = { selectedFilter = "All" }) {
                    Text("All", modifier = Modifier.padding(16.dp))
                }
                Tab(selected = selectedFilter == "Pending", onClick = { selectedFilter = "Pending" }) {
                    Text("Pending", modifier = Modifier.padding(16.dp))
                }
                Tab(selected = selectedFilter == "Completed", onClick = { selectedFilter = "Completed" }) {
                    Text("Completed", modifier = Modifier.padding(16.dp))
                }
            }

            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                val filteredTasks = when (selectedFilter) {
                    "Pending" -> tasks.filter { !it.isCompleted }
                    "Completed" -> tasks.filter { it.isCompleted }
                    else -> tasks
                }
                
                items(filteredTasks) { task ->
                    TaskListItem(task = task) {
                        viewModel.toggleTaskCompletion(task.id)
                    }
                }
            }
        }
    }
}
