package com.example.hustlebuddy.data

import com.example.hustlebuddy.model.*

object DummyData {
    val subjects = listOf(
        Subject("1", "Mathematics", 0xFF4285F4, "math", 0.7f, 15.5f, 85, 12),
        Subject("2", "Physics", 0xFFEA4335, "physics", 0.4f, 8.2f, 72, 8),
        Subject("3", "Computer Science", 0xFF34A853, "code", 0.9f, 25.0f, 95, 20),
        Subject("4", "History", 0xFFFBBC05, "history", 0.2f, 4.0f, 60, 3)
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

    val user = User("Aditya ", "aditya1@example.com", xp = 1250, level = "Consistent Learner", studyScore = 78)
    
    val stats = ProgressStats(12, 45.5f, 850, 7, listOf(0.4f, 0.7f, 0.5f, 0.9f, 0.6f, 0.8f, 1f))

    val insights = listOf(
        Insight("1", "Night Owl", "You study better in the evening 🌙", "NightsStay", InsightType.POSITIVE),
        Insight("2", "Subject Focus", "Math has lowest completion rate", "Warning", InsightType.ALERT),
        Insight("3", "Weekend Break", "You skip tasks on weekends", "EventBusy", InsightType.NEUTRAL)
    )

    val challenges = listOf(
        Challenge("1", "Daily Sprint", "Complete 3 tasks today", 0.66f, false, 50),
        Challenge("2", "Consistency King", "Study 5 days in a row", 1.0f, true, 200),
        Challenge("3", "Quiz Whiz", "Score 90%+ in 3 quizzes", 0.33f, false, 150)
    )

    val dailyGoals = listOf(
        DailyGoal("1", "Study Time", 2.0f, 1.5f, "hours"),
        DailyGoal("2", "Tasks Done", 5.0f, 3.0f, "tasks"),
        DailyGoal("3", "Quiz Taken", 1.0f, 1.0f, "quiz")
    )

    val heatmapData = List(30) { (0..10).random() / 10f } // Mock 30 days of data
}
