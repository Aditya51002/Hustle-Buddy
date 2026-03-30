package com.example.hustlebuddy.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.History
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.hustlebuddy.ui.components.BottomNavigationBar
import com.example.hustlebuddy.ui.components.StudyBuddyCard
import com.example.hustlebuddy.viewmodel.StudyBuddyViewModel
import java.text.SimpleDateFormat
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SessionHistoryScreen(navController: NavController, viewModel: StudyBuddyViewModel) {
    // TODO: track session history and session duration logs
    // For now using dummy history data
    val dummyHistory = listOf(
        Triple("Mathematics", 25, System.currentTimeMillis() - 3600000),
        Triple("Computer Science", 50, System.currentTimeMillis() - 86400000),
        Triple("Physics", 25, System.currentTimeMillis() - 172800000)
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Session History") },
                navigationIcon = {
                    IconButton(onClick = { navController.navigateUp() }) {
                        Icon(Icons.Default.ArrowBack, "Back")
                    }
                }
            )
        },
        bottomBar = { BottomNavigationBar(navController) }
    ) { padding ->
        Column(modifier = Modifier.padding(padding).padding(horizontal = 20.dp)) {
            Spacer(modifier = Modifier.height(16.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(Icons.Default.History, null, tint = MaterialTheme.colorScheme.primary)
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = "Your recent focus sessions", style = MaterialTheme.typography.titleMedium)
            }
            Spacer(modifier = Modifier.height(16.dp))
            
            LazyColumn(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                items(dummyHistory) { (subject, duration, time) ->
                    StudyBuddyCard {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column {
                                Text(text = subject, fontWeight = FontWeight.Bold)
                                val date = SimpleDateFormat("MMM dd, yyyy", Locale.getDefault()).format(Date(time))
                                Text(text = date, style = MaterialTheme.typography.bodySmall, color = Color.Gray)
                            }
                            Text(
                                text = "$duration mins",
                                fontWeight = FontWeight.Black,
                                color = MaterialTheme.colorScheme.primary,
                                fontSize = 18.sp
                            )
                        }
                    }
                }
            }
            
            // TODO: Store session data in backend
        }
    }
}
