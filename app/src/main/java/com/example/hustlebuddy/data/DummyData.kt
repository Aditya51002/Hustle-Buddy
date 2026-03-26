package com.example.hustlebuddy.data

import com.example.hustlebuddy.model.*

object DummyData {
    val subjects = listOf(
        Subject("1", "Mathematics", 0xFF4285F4, "math", 0.7f),
        Subject("2", "Physics", 0xFFEA4335, "physics", 0.4f),
        Subject("3", "Computer Science", 0xFF34A853, "code", 0.9f),
        Subject("4", "History", 0xFFFBBC05, "history", 0.2f)
    )

    val tasks = listOf(
        Task("1", "Calculus Assignment", "Complete exercises 1-10", "1", "2023-11-20", Priority.HIGH),
        Task("2", "Lab Report", "Write summary of experiment", "2", "2023-11-22", Priority.MEDIUM),
        Task("3", "Compose UI Kit", "Build reusable components", "3", "2023-11-18", Priority.HIGH, true),
        Task("4", "French Revolution", "Read chapter 5", "4", "2023-11-25", Priority.LOW)
    )

    val schedules = listOf(
        Schedule("1", "1", "Monday", "09:00 AM", "2 hours", "Focus on derivatives"),
        Schedule("2", "3", "Tuesday", "02:00 PM", "3 hours", "Android Development"),
        Schedule("3", "2", "Wednesday", "10:00 AM", "1.5 hours", "Quantum Mechanics")
    )

    val quizzes = listOf(
        Quiz("1", "DSA Basics", "Computer Science", listOf(
            Question("q1", "What is the time complexity of binary search?", listOf("O(n)", "O(log n)", "O(n^2)", "O(1)"), 1),
            Question("q2", "Which data structure uses LIFO?", listOf("Queue", "Stack", "Linked List", "Tree"), 1)
        )),
        Quiz("2", "Android Fundamentals", "Android", listOf(
            Question("q1", "What is Jetpack Compose?", listOf("A UI Toolkit", "A Database", "A Network Library", "An OS"), 0)
        ))
    )

    val user = User("John Doe", "john.doe@example.com")
    
    val stats = ProgressStats(12, 45.5f, 850, 7)
}
