const express = require('express');
const { 
    getSubjects, createSubject, getSubjectById, updateSubject, deleteSubject,
    getTasks, createTask, updateTask, toggleTaskStatus, deleteTask,
    getSchedules, createSchedule, updateSchedule, deleteSchedule,
    createSession, getSessions,
    getQuizzes, createQuiz, getQuizById, submitQuizResult, getQuizResults,
    getGoals, createGoal, updateGoal, deleteGoal,
    getReminders, createReminder, updateReminder, deleteReminder,
    getAnalyticsSummary, getWeeklyAnalytics, getHeatmapData, getInsights,
    getGamificationData
} = require('../controllers/study-buddy.controller');
const { protect } = require('../middlewares/auth.middleware');

const router = express.Router();

router.use(protect);

// Subjects
router.route('/subjects')
    .get(getSubjects)
    .post(createSubject);

router.route('/subjects/:id')
    .get(getSubjectById)
    .put(updateSubject)
    .delete(deleteSubject);

// Tasks
router.route('/tasks')
    .get(getTasks)
    .post(createTask);

router.route('/tasks/:id')
    .put(updateTask)
    .delete(deleteTask);

router.patch('/tasks/:id/status', toggleTaskStatus);

// Schedules
router.route('/schedules')
    .get(getSchedules)
    .post(createSchedule);

router.route('/schedules/:id')
    .put(updateSchedule)
    .delete(deleteSchedule);

// Sessions
router.route('/sessions')
    .get(getSessions)
    .post(createSession);

// Quizzes
router.get('/quizzes', getQuizzes);
router.post('/quizzes', createQuiz); // Admin or Seed
router.get('/quizzes/:id', getQuizById);
router.post('/quizzes/submit', submitQuizResult);
router.get('/quizzes/results', getQuizResults);

// Goals
router.route('/goals')
    .get(getGoals)
    .post(createGoal);

router.route('/goals/:id')
    .put(updateGoal)
    .delete(deleteGoal);

// Reminders
router.route('/reminders')
    .get(getReminders)
    .post(createReminder);

router.route('/reminders/:id')
    .put(updateReminder)
    .delete(deleteReminder);

// Analytics
router.get('/analytics/summary', getAnalyticsSummary);
router.get('/analytics/weekly', getWeeklyAnalytics);
router.get('/analytics/heatmap', getHeatmapData);
router.get('/analytics/insights', getInsights);

// Gamification
router.get('/gamification', getGamificationData);

module.exports = router;
