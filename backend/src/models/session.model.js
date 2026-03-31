const mongoose = require('mongoose');

const sessionSchema = new mongoose.Schema({
    userId: {
        type: mongoose.Schema.Types.ObjectId,
        ref: 'User',
        required: true
    },
    subjectId: {
        type: mongoose.Schema.Types.ObjectId,
        ref: 'Subject',
        required: true
    },
    taskId: {
        type: mongoose.Schema.Types.ObjectId,
        ref: 'Task'
    },
    mode: {
        type: String,
        enum: ['pomodoro', 'short-break', 'long-break', 'custom'],
        default: 'pomodoro'
    },
    plannedMinutes: {
        type: Number
    },
    actualMinutes: {
        type: Number,
        required: true
    },
    completed: {
        type: Boolean,
        default: true
    },
    startedAt: {
        type: Date,
        default: Date.now
    },
    endedAt: {
        type: Date
    }
}, {
    timestamps: true
});

module.exports = mongoose.model('Session', sessionSchema);
