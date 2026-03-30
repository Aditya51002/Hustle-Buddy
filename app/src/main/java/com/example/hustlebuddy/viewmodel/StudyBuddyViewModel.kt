package com.example.hustlebuddy.viewmodel

import androidx.lifecycle.ViewModel
import com.example.hustlebuddy.data.DummyData
import com.example.hustlebuddy.model.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class StudyBuddyViewModel : ViewModel() {

    private val _subjects = MutableStateFlow(DummyData.subjects)
    val subjects: StateFlow<List<Subject>> = _subjects.asStateFlow()

    private val _tasks = MutableStateFlow(DummyData.tasks)
    val tasks: StateFlow<List<Task>> = _tasks.asStateFlow()

    private val _schedules = MutableStateFlow(DummyData.schedules)
    val schedules: StateFlow<List<Schedule>> = _schedules.asStateFlow()

    private val _quizzes = MutableStateFlow(DummyData.quizzes)
    val quizzes: StateFlow<List<Quiz>> = _quizzes.asStateFlow()

    private val _user = MutableStateFlow(DummyData.user)
    val user: StateFlow<User> = _user.asStateFlow()

    private val _stats = MutableStateFlow(DummyData.stats)
    val stats: StateFlow<ProgressStats> = _stats.asStateFlow()

    private val _insights = MutableStateFlow(DummyData.insights)
    val insights: StateFlow<List<Insight>> = _insights.asStateFlow()

    private val _challenges = MutableStateFlow(DummyData.challenges)
    val challenges: StateFlow<List<Challenge>> = _challenges.asStateFlow()

    private val _dailyGoals = MutableStateFlow(DummyData.dailyGoals)
    val dailyGoals: StateFlow<List<DailyGoal>> = _dailyGoals.asStateFlow()

    private val _heatmapData = MutableStateFlow(DummyData.heatmapData)
    val heatmapData: StateFlow<List<Float>> = _heatmapData.asStateFlow()

    // Methods to handle UI logic (Dummy for now)
    
    fun addSubject(subject: Subject) {
        // TODO: Save to database here
        _subjects.value = _subjects.value + subject
    }

    fun addTask(task: Task) {
        // TODO: Save to database here
        _tasks.value = _tasks.value + task
        updateXP(10) // Earn 10 XP for adding task
    }

    fun toggleTaskCompletion(taskId: String) {
        // TODO: Update in database here
        _tasks.value = _tasks.value.map {
            if (it.id == taskId) {
                val completed = !it.isCompleted
                if (completed) updateXP(50) // Earn 50 XP for completing task
                it.copy(isCompleted = completed)
            } else it
        }
    }

    private fun updateXP(amount: Int) {
        // TODO: Store XP and level in backend
        val currentUser = _user.value
        _user.value = currentUser.copy(xp = currentUser.xp + amount)
    }

    fun addSchedule(schedule: Schedule) {
        // TODO: Save to database here
        _schedules.value = _schedules.value + schedule
    }

    fun login(email: String, pass: String) {
        // TODO: Connect Firebase authentication here later
    }

    fun signup(name: String, email: String, pass: String) {
        // TODO: Connect Firebase authentication here later
    }
}
