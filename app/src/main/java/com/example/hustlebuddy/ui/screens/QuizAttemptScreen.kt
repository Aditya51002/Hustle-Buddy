package com.example.hustlebuddy.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.hustlebuddy.navigation.Screen
import com.example.hustlebuddy.viewmodel.StudyBuddyViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun QuizAttemptScreen(navController: NavController, viewModel: StudyBuddyViewModel, quizId: String) {
    val quizzes by viewModel.quizzes.collectAsState()
    val quiz = quizzes.find { it.id == quizId } ?: return

    var currentQuestionIndex by remember { mutableIntStateOf(0) }
    var selectedOptionIndex by remember { mutableIntStateOf(-1) }
    var score by remember { mutableIntStateOf(0) }

    val currentQuestion = quiz.questions[currentQuestionIndex]

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(quiz.title) },
                navigationIcon = {
                    IconButton(onClick = { navController.navigateUp() }) {
                        Icon(Icons.Default.Close, "Quit")
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(24.dp)
        ) {
            LinearProgressIndicator(
                progress = { (currentQuestionIndex + 1).toFloat() / quiz.questions.size },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(32.dp))
            
            Text(
                text = "Question ${currentQuestionIndex + 1} of ${quiz.questions.size}",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.outline
            )
            
            Spacer(modifier = Modifier.height(16.dp))
            
            Text(
                text = currentQuestion.question,
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold
            )
            
            Spacer(modifier = Modifier.height(32.dp))

            // TODO: Add comment where future dynamic quiz data will be fetched
            Column(Modifier.selectableGroup()) {
                currentQuestion.options.forEachIndexed { index, option ->
                    val isSelected = selectedOptionIndex == index
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp)
                            .selectable(
                                selected = isSelected,
                                onClick = { selectedOptionIndex = index },
                                role = Role.RadioButton
                            ),
                        border = BorderStroke(
                            width = 2.dp,
                            color = if (isSelected) MaterialTheme.colorScheme.primary else Color.Transparent
                        ),
                        colors = CardDefaults.cardColors(
                            containerColor = if (isSelected) MaterialTheme.colorScheme.primaryContainer else MaterialTheme.colorScheme.surfaceVariant
                        )
                    ) {
                        Row(
                            modifier = Modifier
                                .padding(20.dp)
                                .fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            RadioButton(selected = isSelected, onClick = null)
                            Text(
                                text = option,
                                modifier = Modifier.padding(start = 16.dp),
                                fontSize = 16.sp
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.weight(1f))

            Button(
                onClick = {
                    if (selectedOptionIndex == currentQuestion.correctAnswerIndex) {
                        score++
                    }
                    
                    if (currentQuestionIndex < quiz.questions.size - 1) {
                        currentQuestionIndex++
                        selectedOptionIndex = -1
                    } else {
                        navController.navigate(Screen.QuizResult.createRoute(score, quiz.questions.size)) {
                            popUpTo(Screen.Quiz.route)
                        }
                    }
                },
                modifier = Modifier.fillMaxWidth().height(56.dp),
                enabled = selectedOptionIndex != -1,
                shape = MaterialTheme.shapes.medium
            ) {
                Text(if (currentQuestionIndex < quiz.questions.size - 1) "Next Question" else "Submit")
            }
        }
    }
}
