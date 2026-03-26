package com.example.hustlebuddy.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocalFireDepartment
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.Timer
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.hustlebuddy.ui.components.SectionHeader
import com.example.hustlebuddy.ui.components.StudyBuddyCard
import com.example.hustlebuddy.viewmodel.StudyBuddyViewModel

@OptIn(Material3Api::class)
@Composable
fun ProgressScreen(navController: NavController, viewModel: StudyBuddyViewModel) {
    val stats by viewModel.stats.collectAsState()

    Scaffold(
        topBar = { TopAppBar(title = { Text("Progress & Stats") }) },
        bottomBar = { BottomNavigationBar(navController) }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(horizontal = 20.dp)
        ) {
            item {
                Spacer(modifier = Modifier.height(16.dp))
                StudyBuddyCard(containerColor = MaterialTheme.colorScheme.primary) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text = "Keep it up!",
                                color = Color.White,
                                style = MaterialTheme.typography.titleMedium
                            )
                            Text(
                                text = "You're on a ${stats.streakCount} day streak!",
                                color = Color.White,
                                fontSize = 24.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                        Icon(
                            Icons.Default.LocalFireDepartment,
                            contentDescription = null,
                            tint = Color(0xFFFFB74D),
                            modifier = Modifier.size(64.dp)
                        )
                    }
                }
                Spacer(modifier = Modifier.height(24.dp))
            }

            item {
                SectionHeader(title = "Learning Overview")
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    StatCard(
                        modifier = Modifier.weight(1f),
                        label = "Study Hours",
                        value = "${stats.totalStudyHours}h",
                        icon = Icons.Default.Timer,
                        color = Color(0xFF4285F4)
                    )
                    StatCard(
                        modifier = Modifier.weight(1f),
                        label = "Tasks Done",
                        value = "${stats.completedTasks}",
                        icon = Icons.Default.Star,
                        color = Color(0xFF34A853)
                    )
                }
                Spacer(modifier = Modifier.height(24.dp))
            }

            item {
                SectionHeader(title = "Weekly Performance")
                // TODO: Add comment where analytics/progress backend will connect later
                StudyBuddyCard {
                    Text(
                        text = "Last 7 Days Activity",
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(bottom = 16.dp)
                    )
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(150.dp),
                        horizontalArrangement = Arrangement.SpaceEvenly,
                        verticalAlignment = Alignment.Bottom
                    ) {
                        val heights = listOf(0.4f, 0.7f, 0.5f, 0.9f, 0.6f, 0.8f, 1f)
                        val days = listOf("M", "T", "W", "T", "F", "S", "S")
                        
                        heights.forEachIndexed { index, h ->
                            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                LinearProgressIndicator(
                                    progress = h,
                                    modifier = Modifier
                                        .height(100.dp)
                                        .width(12.dp),
                                    color = MaterialTheme.colorScheme.primary,
                                    trackColor = MaterialTheme.colorScheme.surfaceVariant
                                )
                                Spacer(modifier = Modifier.height(8.dp))
                                Text(text = days[index], style = MaterialTheme.typography.bodySmall)
                            }
                        }
                    }
                }
                Spacer(modifier = Modifier.height(24.dp))
            }
        }
    }
}

@Composable
fun StatCard(label: String, value: String, icon: ImageVector, color: Color, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(containerColor = color.copy(alpha = 0.1f)),
        shape = MaterialTheme.shapes.large
    ) {
        Column(
            modifier = Modifier.padding(20.dp)
        ) {
            Icon(icon, contentDescription = null, tint = color)
            Spacer(modifier = Modifier.height(12.dp))
            Text(text = value, fontSize = 22.sp, fontWeight = FontWeight.Bold)
            Text(text = label, style = MaterialTheme.typography.bodySmall, color = Color.Gray)
        }
    }
}
