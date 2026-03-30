package com.example.hustlebuddy.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import com.example.hustlebuddy.ui.components.*
import com.example.hustlebuddy.viewmodel.StudyBuddyViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProgressScreen(navController: NavController, viewModel: StudyBuddyViewModel) {
    val stats by viewModel.stats.collectAsState()
    val user by viewModel.user.collectAsState()
    val heatmapData by viewModel.heatmapData.collectAsState()
    val subjects by viewModel.subjects.collectAsState()

    Scaffold(
        topBar = { TopAppBar(title = { Text("Performance Analytics") }) },
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
                StudyScoreCard(score = user.studyScore)
                Spacer(modifier = Modifier.height(24.dp))
            }

            item {
                HeatmapGrid(data = heatmapData)
                Spacer(modifier = Modifier.height(24.dp))
            }

            item {
                ProductivityComparisonCard()
                Spacer(modifier = Modifier.height(24.dp))
            }

            item {
                SectionHeader(title = "Subject Performance")
            }
            
            items(subjects) { subject ->
                SubjectAnalyticsCard(subject = subject)
                Spacer(modifier = Modifier.height(12.dp))
            }

            item {
                SectionHeader(title = "Weekly Performance")
                // TODO: x5 Fetch analytics data
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
                        val days = listOf("M", "T", "W", "T", "F", "S", "S")
                        
                        stats.weeklyActivity.forEachIndexed { index, h ->
                            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                LinearProgressIndicator(
                                    progress = { h },
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
