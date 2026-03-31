const Subject = require('../models/subject.model');
const Task = require('../models/task.model');
const Schedule = require('../models/schedule.model');
const Session = require('../models/session.model');
const Quiz = require('../models/quiz.model');
const QuizResult = require('../models/quizResult.model');
const Goal = require('../models/goal.model');
const Reminder = require('../models/reminder.model');
const User = require('../models/user.model');
const ApiError = require('../utils/ApiError');

class StudyBuddyService {
    // Subjects
    async createSubject(userId, subjectData) {
        return await Subject.create({ ...subjectData, userId });
    }

    async getSubjects(userId) {
        return await Subject.find({ userId });
    }

    async getSubjectById(userId, subjectId) {
        const subject = await Subject.findOne({ _id: subjectId, userId });
        if (!subject) throw new ApiError(404, 'Subject not found');
        return subject;
    }

    async updateSubject(userId, subjectId, updateData) {
        const subject = await Subject.findOneAndUpdate(
            { _id: subjectId, userId },
            updateData,
            { new: true, runValidators: true }
        );
        if (!subject) throw new ApiError(404, 'Subject not found');
        return subject;
    }

    async deleteSubject(userId, subjectId) {
        const subject = await Subject.findOneAndDelete({ _id: subjectId, userId });
        if (!subject) throw new ApiError(404, 'Subject not found');
        return subject;
    }

    // Tasks
    async createTask(userId, taskData) {
        return await Task.create({ ...taskData, userId });
    }

    async getTasks(userId, filters = {}) {
        const query = { userId };
        if (filters.subjectId) query.subjectId = filters.subjectId;
        if (filters.completed !== undefined) query.completed = filters.completed === 'true';
        if (filters.priority) query.priority = filters.priority;
        if (filters.search) query.title = { $regex: filters.search, $options: 'i' };

        return await Task.find(query).populate('subjectId', 'subjectName color');
    }

    async updateTask(userId, taskId, updateData) {
        const task = await Task.findOneAndUpdate(
            { _id: taskId, userId },
            updateData,
            { new: true, runValidators: true }
        );
        if (!task) throw new ApiError(404, 'Task not found');
        return task;
    }

    async toggleTaskStatus(userId, taskId) {
        const task = await Task.findOne({ _id: taskId, userId });
        if (!task) throw new ApiError(404, 'Task not found');
        task.completed = !task.completed;
        await task.save();

        if (task.completed) await this.addXP(userId, 50);

        return task;
    }

    async deleteTask(userId, taskId) {
        const task = await Task.findOneAndDelete({ _id: taskId, userId });
        if (!task) throw new ApiError(404, 'Task not found');
        return task;
    }

    // Schedules
    async createSchedule(userId, scheduleData) {
        return await Schedule.create({ ...scheduleData, userId });
    }

    async getSchedules(userId) {
        return await Schedule.find({ userId }).populate('subjectId', 'subjectName color');
    }

    async updateSchedule(userId, scheduleId, updateData) {
        const schedule = await Schedule.findOneAndUpdate(
            { _id: scheduleId, userId },
            updateData,
            { new: true, runValidators: true }
        );
        if (!schedule) throw new ApiError(404, 'Schedule not found');
        return schedule;
    }

    async deleteSchedule(userId, scheduleId) {
        const schedule = await Schedule.findOneAndDelete({ _id: scheduleId, userId });
        if (!schedule) throw new ApiError(404, 'Schedule not found');
        return schedule;
    }

    // Sessions
    async createSession(userId, sessionData) {
        const session = await Session.create({ ...sessionData, userId });
        
        // Update subject total study time
        await Subject.findByIdAndUpdate(sessionData.subjectId, {
            $inc: { totalStudyMinutes: sessionData.actualMinutes }
        });

        // Add XP to user (2 XP per minute)
        await this.addXP(userId, Math.floor(sessionData.actualMinutes * 2));

        return session;
    }

    async getSessions(userId, filters = {}) {
        const query = { userId };
        if (filters.subjectId) query.subjectId = filters.subjectId;
        if (filters.date) {
            const start = new Date(filters.date);
            start.setHours(0,0,0,0);
            const end = new Date(filters.date);
            end.setHours(23,59,59,999);
            query.startedAt = { $gte: start, $lte: end };
        }
        return await Session.find(query).populate('subjectId', 'subjectName color');
    }

    // Quizzes
    async createQuiz(quizData) {
        return await Quiz.create(quizData);
    }

    async getQuizzes(subject) {
        const query = subject ? { subject } : {};
        return await Quiz.find(query);
    }

    async getQuizById(quizId) {
        const quiz = await Quiz.findById(quizId);
        if (!quiz) throw new ApiError(404, 'Quiz not found');
        return quiz;
    }

    async submitQuizResult(userId, resultData) {
        const result = await QuizResult.create({ ...resultData, userId });
        // Add XP for taking quiz (Score * 10)
        await this.addXP(userId, resultData.score * 10);
        return result;
    }

    async getQuizResults(userId) {
        return await QuizResult.find({ userId }).sort({ attemptedAt: -1 });
    }

    // Goals
    async createGoal(userId, goalData) {
        return await Goal.create({ ...goalData, userId });
    }

    async getGoals(userId) {
        return await Goal.find({ userId });
    }

    async updateGoal(userId, goalId, updateData) {
        const goal = await Goal.findOneAndUpdate(
            { _id: goalId, userId },
            updateData,
            { new: true, runValidators: true }
        );
        if (!goal) throw new ApiError(404, 'Goal not found');
        return goal;
    }

    async deleteGoal(userId, goalId) {
        const goal = await Goal.findOneAndDelete({ _id: goalId, userId });
        if (!goal) throw new ApiError(404, 'Goal not found');
        return goal;
    }

    // Reminders
    async createReminder(userId, reminderData) {
        return await Reminder.create({ ...reminderData, userId });
    }

    async getReminders(userId) {
        return await Reminder.find({ userId });
    }

    async updateReminder(userId, reminderId, updateData) {
        const reminder = await Reminder.findOneAndUpdate(
            { _id: reminderId, userId },
            updateData,
            { new: true, runValidators: true }
        );
        if (!reminder) throw new ApiError(404, 'Reminder not found');
        return reminder;
    }

    async deleteReminder(userId, reminderId) {
        const reminder = await Reminder.findOneAndDelete({ _id: reminderId, userId });
        if (!reminder) throw new ApiError(404, 'Reminder not found');
        return reminder;
    }

    // Gamification & XP
    async addXP(userId, amount) {
        const user = await User.findById(userId);
        user.xp += amount;
        
        // Level logic
        if (user.xp > 10000) user.level = 'Study Guru';
        else if (user.xp > 5000) user.level = 'Study Master';
        else if (user.xp > 2000) user.level = 'Advanced Learner';
        else if (user.xp > 500) user.level = 'Consistent Learner';
        
        await user.save();
        return user;
    }

    // Analytics
    async getAnalyticsSummary(userId) {
        const totalSessions = await Session.countDocuments({ userId });
        const totalTasks = await Task.countDocuments({ userId, completed: true });
        
        const sessions = await Session.find({ userId });
        const totalMinutes = sessions.reduce((acc, curr) => acc + curr.actualMinutes, 0);

        const subjects = await Subject.find({ userId });
        const subjectStats = subjects.map(s => ({
            name: s.subjectName,
            time: s.totalStudyMinutes,
            color: s.color
        }));

        // Calculate Study Score (0-100)
        const studyScore = Math.min(100, Math.floor((totalTasks * 5) + (totalMinutes / 60) * 2));

        return {
            totalStudyTime: totalMinutes,
            completedTasks: totalTasks,
            totalSessions,
            studyScore,
            subjectStats
        };
    }

    async getWeeklyAnalytics(userId) {
        const last7Days = new Date();
        last7Days.setDate(last7Days.getDate() - 7);

        const sessions = await Session.find({
            userId,
            startedAt: { $gte: last7Days }
        });

        // Group by day of week
        const days = ['Sun', 'Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat'];
        const weeklyData = days.map(day => ({ day, minutes: 0 }));

        sessions.forEach(s => {
            const dayIndex = new Date(s.startedAt).getDay();
            weeklyData[dayIndex].minutes += s.actualMinutes;
        });

        return weeklyData;
    }

    async getHeatmapData(userId) {
        const last30Days = new Date();
        last30Days.setDate(last30Days.getDate() - 30);

        const sessions = await Session.find({
            userId,
            startedAt: { $gte: last30Days }
        });

        // Return values between 0-1 for heatmap intensity
        const heatmap = Array(30).fill(0);
        sessions.forEach(s => {
            const diffTime = Math.abs(new Date() - new Date(s.startedAt));
            const diffDays = Math.floor(diffTime / (1000 * 60 * 60 * 24));
            if (diffDays < 30) {
                heatmap[29 - diffDays] += s.actualMinutes;
            }
        });

        const maxMinutes = Math.max(...heatmap, 60);
        return heatmap.map(m => Math.min(1, m / maxMinutes));
    }
}

module.exports = new StudyBuddyService();
