package com.example.hustlebuddy.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.hustlebuddy.ui.screens.*
import com.example.hustlebuddy.viewmodel.StudyBuddyViewModel

sealed class Screen(val route: String) {
    object Splash : Screen("splash")
    object Login : Screen("login")
    object Signup : Screen("signup")
    object Home : Screen("home")
    object Subjects : Screen("subjects")
    object AddSubject : Screen("add_subject")
    object Tasks : Screen("tasks")
    object AddTask : Screen("add_task")
    object Schedule : Screen("schedule")
    object AddSchedule : Screen("add_schedule")
    object Pomodoro : Screen("pomodoro")
    object Quiz : Screen("quiz")
    object QuizAttempt : Screen("quiz_attempt/{quizId}") {
        fun createRoute(quizId: String) = "quiz_attempt/$quizId"
    }
    object QuizResult : Screen("quiz_result/{score}/{total}") {
        fun createRoute(score: Int, total: Int) = "quiz_result/$score/$total"
    }
    object Progress : Screen("progress")
    object Profile : Screen("profile")
    object Notifications : Screen("notifications")
    object Insights : Screen("insights")
    object Challenges : Screen("challenges")
    object SessionHistory : Screen("session_history")
}

@Composable
fun NavGraph(navController: NavHostController) {
    val viewModel: StudyBuddyViewModel = viewModel()
    
    NavHost(
        navController = navController,
        startDestination = Screen.Splash.route
    ) {
        composable(Screen.Splash.route) {
            SplashScreen(navController)
        }
        composable(Screen.Login.route) {
            LoginScreen(navController)
        }
        composable(Screen.Signup.route) {
            SignupScreen(navController)
        }
        composable(Screen.Home.route) {
            HomeScreen(navController, viewModel)
        }
        composable(Screen.Subjects.route) {
            SubjectScreen(navController, viewModel)
        }
        composable(Screen.AddSubject.route) {
            AddSubjectScreen(navController, viewModel)
        }
        composable(Screen.Tasks.route) {
            TaskScreen(navController, viewModel)
        }
        composable(Screen.AddTask.route) {
            AddTaskScreen(navController, viewModel)
        }
        composable(Screen.Schedule.route) {
            ScheduleScreen(navController, viewModel)
        }
        composable(Screen.AddSchedule.route) {
            AddScheduleScreen(navController, viewModel)
        }
        composable(Screen.Pomodoro.route) {
            PomodoroScreen(navController)
        }
        composable(Screen.Quiz.route) {
            QuizScreen(navController, viewModel)
        }
        composable(Screen.QuizAttempt.route) { backStackEntry ->
            val quizId = backStackEntry.arguments?.getString("quizId") ?: ""
            QuizAttemptScreen(navController, viewModel, quizId)
        }
        composable(Screen.QuizResult.route) { backStackEntry ->
            val score = backStackEntry.arguments?.getString("score")?.toInt() ?: 0
            val total = backStackEntry.arguments?.getString("total")?.toInt() ?: 0
            QuizResultScreen(navController, score, total)
        }
        composable(Screen.Progress.route) {
            ProgressScreen(navController, viewModel)
        }
        composable(Screen.Profile.route) {
            ProfileScreen(navController, viewModel)
        }
        composable(Screen.Notifications.route) {
            NotificationsScreen(navController)
        }
        composable(Screen.Insights.route) {
            InsightsScreen(navController, viewModel)
        }
        composable(Screen.Challenges.route) {
            ChallengesScreen(navController, viewModel)
        }
        composable(Screen.SessionHistory.route) {
            SessionHistoryScreen(navController, viewModel)
        }
    }
}
