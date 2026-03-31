const asyncHandler = require('../utils/asyncHandler');
const ApiResponse = require('../utils/ApiResponse');
const studyBuddyService = require('../services/study-buddy.service');

// --- Subjects ---
const getSubjects = asyncHandler(async (req, res) => {
    const subjects = await studyBuddyService.getSubjects(req.user.id);
    res.status(200).json(new ApiResponse(200, subjects, "Subjects fetched successfully"));
});

const createSubject = asyncHandler(async (req, res) => {
    const subject = await studyBuddyService.createSubject(req.user.id, req.body);
    res.status(201).json(new ApiResponse(201, subject, "Subject created successfully"));
});

const getSubjectById = asyncHandler(async (req, res) => {
    const subject = await studyBuddyService.getSubjectById(req.user.id, req.params.id);
    res.status(200).json(new ApiResponse(200, subject, "Subject fetched successfully"));
});

const updateSubject = asyncHandler(async (req, res) => {
    const subject = await studyBuddyService.updateSubject(req.user.id, req.params.id, req.body);
    res.status(200).json(new ApiResponse(200, subject, "Subject updated successfully"));
});

const deleteSubject = asyncHandler(async (req, res) => {
    await studyBuddyService.deleteSubject(req.user.id, req.params.id);
    res.status(200).json(new ApiResponse(200, null, "Subject deleted successfully"));
});

// --- Tasks ---
const getTasks = asyncHandler(async (req, res) => {
    const tasks = await studyBuddyService.getTasks(req.user.id, req.query);
    res.status(200).json(new ApiResponse(200, tasks, "Tasks fetched successfully"));
});

const createTask = asyncHandler(async (req, res) => {
    const task = await studyBuddyService.createTask(req.user.id, req.body);
    res.status(201).json(new ApiResponse(201, task, "Task created successfully"));
});

const updateTask = asyncHandler(async (req, res) => {
    const task = await studyBuddyService.updateTask(req.user.id, req.params.id, req.body);
    res.status(200).json(new ApiResponse(200, task, "Task updated successfully"));
});

const toggleTaskStatus = asyncHandler(async (req, res) => {
    const task = await studyBuddyService.toggleTaskStatus(req.user.id, req.params.id);
    res.status(200).json(new ApiResponse(200, task, "Task status updated"));
});

const deleteTask = asyncHandler(async (req, res) => {
    await studyBuddyService.deleteTask(req.user.id, req.params.id);
    res.status(200).json(new ApiResponse(200, null, "Task deleted successfully"));
});

// --- Schedules ---
const getSchedules = asyncHandler(async (req, res) => {
    const schedules = await studyBuddyService.getSchedules(req.user.id);
    res.status(200).json(new ApiResponse(200, schedules, "Schedules fetched successfully"));
});

const createSchedule = asyncHandler(async (req, res) => {
    const schedule = await studyBuddyService.createSchedule(req.user.id, req.body);
    res.status(201).json(new ApiResponse(201, schedule, "Schedule created successfully"));
});

const updateSchedule = asyncHandler(async (req, res) => {
    const schedule = await studyBuddyService.updateSchedule(req.user.id, req.params.id, req.body);
    res.status(200).json(new ApiResponse(200, schedule, "Schedule updated successfully"));
});

const deleteSchedule = asyncHandler(async (req, res) => {
    await studyBuddyService.deleteSchedule(req.user.id, req.params.id);
    res.status(200).json(new ApiResponse(200, null, "Schedule deleted successfully"));
});

// --- Sessions ---
const createSession = asyncHandler(async (req, res) => {
    const session = await studyBuddyService.createSession(req.user.id, req.body);
    res.status(201).json(new ApiResponse(201, session, "Session logged successfully"));
});

const getSessions = asyncHandler(async (req, res) => {
    const sessions = await studyBuddyService.getSessions(req.user.id, req.query);
    res.status(200).json(new ApiResponse(200, sessions, "Sessions fetched successfully"));
});

// --- Quizzes ---
const getQuizzes = asyncHandler(async (req, res) => {
    const quizzes = await studyBuddyService.getQuizzes(req.query.subject);
    res.status(200).json(new ApiResponse(200, quizzes, "Quizzes fetched successfully"));
});

const createQuiz = asyncHandler(async (req, res) => {
    const quiz = await studyBuddyService.createQuiz(req.body);
    res.status(201).json(new ApiResponse(201, quiz, "Quiz created successfully"));
});

const getQuizById = asyncHandler(async (req, res) => {
    const quiz = await studyBuddyService.getQuizById(req.params.id);
    res.status(200).json(new ApiResponse(200, quiz, "Quiz fetched successfully"));
});

const submitQuizResult = asyncHandler(async (req, res) => {
    const result = await studyBuddyService.submitQuizResult(req.user.id, req.body);
    res.status(201).json(new ApiResponse(201, result, "Quiz result submitted successfully"));
});

const getQuizResults = asyncHandler(async (req, res) => {
    const results = await studyBuddyService.getQuizResults(req.user.id);
    res.status(200).json(new ApiResponse(200, results, "Quiz results fetched successfully"));
});

// --- Goals ---
const getGoals = asyncHandler(async (req, res) => {
    const goals = await studyBuddyService.getGoals(req.user.id);
    res.status(200).json(new ApiResponse(200, goals, "Goals fetched successfully"));
});

const createGoal = asyncHandler(async (req, res) => {
    const goal = await studyBuddyService.createGoal(req.user.id, req.body);
    res.status(201).json(new ApiResponse(201, goal, "Goal created successfully"));
});

const updateGoal = asyncHandler(async (req, res) => {
    const goal = await studyBuddyService.updateGoal(req.user.id, req.params.id, req.body);
    res.status(200).json(new ApiResponse(200, goal, "Goal updated successfully"));
});

const deleteGoal = asyncHandler(async (req, res) => {
    await studyBuddyService.deleteGoal(req.user.id, req.params.id);
    res.status(200).json(new ApiResponse(200, null, "Goal deleted successfully"));
});

// --- Reminders ---
const getReminders = asyncHandler(async (req, res) => {
    const reminders = await studyBuddyService.getReminders(req.user.id);
    res.status(200).json(new ApiResponse(200, reminders, "Reminders fetched successfully"));
});

const createReminder = asyncHandler(async (req, res) => {
    const reminder = await studyBuddyService.createReminder(req.user.id, req.body);
    res.status(201).json(new ApiResponse(201, reminder, "Reminder created successfully"));
});

const updateReminder = asyncHandler(async (req, res) => {
    const reminder = await studyBuddyService.updateReminder(req.user.id, req.params.id, req.body);
    res.status(200).json(new ApiResponse(200, reminder, "Reminder updated successfully"));
});

const deleteReminder = asyncHandler(async (req, res) => {
    await studyBuddyService.deleteReminder(req.user.id, req.params.id);
    res.status(200).json(new ApiResponse(200, null, "Reminder deleted successfully"));
});

// --- Analytics ---
const getAnalyticsSummary = asyncHandler(async (req, res) => {
    const summary = await studyBuddyService.getAnalyticsSummary(req.user.id);
    res.status(200).json(new ApiResponse(200, summary, "Analytics summary fetched"));
});

const getWeeklyAnalytics = asyncHandler(async (req, res) => {
    const weeklyData = await studyBuddyService.getWeeklyAnalytics(req.user.id);
    res.status(200).json(new ApiResponse(200, weeklyData, "Weekly analytics fetched"));
});

const getHeatmapData = asyncHandler(async (req, res) => {
    const heatmap = await studyBuddyService.getHeatmapData(req.user.id);
    res.status(200).json(new ApiResponse(200, heatmap, "Heatmap data fetched"));
});

const getInsights = asyncHandler(async (req, res) => {
    // Placeholder logic for AI insights
    const insights = [
        { id: "1", title: "Morning Focus", description: "You are most productive between 8 AM and 10 AM.", type: "POSITIVE" },
        { id: "2", title: "Subject Warning", description: "Math has the lowest completion rate this week.", type: "ALERT" }
    ];
    res.status(200).json(new ApiResponse(200, insights, "Study insights fetched"));
});

// --- Gamification ---
const getGamificationData = asyncHandler(async (req, res) => {
    const user = req.user;
    const data = {
        xp: user.xp,
        level: user.level,
        badges: user.badges,
        streak: user.streak,
        studyScore: user.studyScore
    };
    res.status(200).json(new ApiResponse(200, data, "Gamification data fetched"));
});

module.exports = {
    getSubjects,
    createSubject,
    getSubjectById,
    updateSubject,
    deleteSubject,
    getTasks,
    createTask,
    updateTask,
    toggleTaskStatus,
    deleteTask,
    getSchedules,
    createSchedule,
    updateSchedule,
    deleteSchedule,
    createSession,
    getSessions,
    getQuizzes,
    createQuiz,
    getQuizById,
    submitQuizResult,
    getQuizResults,
    getGoals,
    createGoal,
    updateGoal,
    deleteGoal,
    getReminders,
    createReminder,
    updateReminder,
    deleteReminder,
    getAnalyticsSummary,
    getWeeklyAnalytics,
    getHeatmapData,
    getInsights,
    getGamificationData
};
