package com.example.hustlebuddy.model

import androidx.compose.ui.graphics.Color

data class Subject(
    val id: String,
    val name: String,
    val color: Long, // Use Long for Color representation
    val icon: String,
    val progress: Float
)

data class Task(
    val id: String,
    val title: String,
    val description: String,
    val subjectId: String,
    val deadline: String,
    val priority: Priority,
    val isCompleted: Boolean = false
)

enum class Priority {
    LOW, MEDIUM, HIGH
}

data class Schedule(
    val id: String,
    val subjectId: String,
    val date: String,
    val startTime: String,
    val duration: String,
    val notes: String
)

data class Question(
    val id: String,
    val question: String,
    val options: List<String>,
    val correctAnswerIndex: Int
)

data class Quiz(
    val id: String,
    val title: String,
    val category: String,
    val questions: List<Question>
)

data class User(
    val name: String,
    val email: String,
    val profileImageUrl: String? = null
)

data class ProgressStats(
    val completedTasks: Int,
    val totalStudyHours: Float,
    val quizScore: Int,
    val streakCount: Int
)
