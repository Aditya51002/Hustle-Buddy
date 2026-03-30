package com.example.hustlebuddy.model

import androidx.compose.ui.graphics.Color

data class Subject(
    val id: String,
    val name: String,
    val color: Long,
    val icon: String,
    val progress: Float,
    val studyTimeHours: Float = 0f,
    val avgQuizScore: Int = 0,
    val tasksCompleted: Int = 0
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
    val profileImageUrl: String? = null,
    val xp: Int = 0,
    val level: String = "Beginner",
    val studyScore: Int = 0
)

data class ProgressStats(
    val completedTasks: Int,
    val totalStudyHours: Float,
    val quizScore: Int,
    val streakCount: Int,
    val weeklyActivity: List<Float> // 7 values for the week
)

data class Insight(
    val id: String,
    val title: String,
    val description: String,
    val icon: String,
    val type: InsightType
)

enum class InsightType {
    POSITIVE, NEUTRAL, ALERT
}

data class Challenge(
    val id: String,
    val title: String,
    val description: String,
    val progress: Float,
    val isCompleted: Boolean = false,
    val rewardXp: Int
)

data class StudySession(
    val id: String,
    val subjectId: String,
    val durationMinutes: Int,
    val timestamp: Long,
    val mode: String
)

data class DailyGoal(
    val id: String,
    val title: String,
    val targetValue: Float,
    val currentValue: Float,
    val unit: String
)
