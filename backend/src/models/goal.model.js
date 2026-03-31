const mongoose = require('mongoose');

const goalSchema = new mongoose.Schema({
    userId: {
        type: mongoose.Schema.Types.ObjectId,
        ref: 'User',
        required: true
    },
    title: {
        type: String,
        required: true,
        trim: true
    },
    type: {
        type: String,
        enum: ['study-hours', 'tasks-completed', 'quizzes-taken', 'custom'],
        required: true
    },
    targetValue: {
        type: Number,
        required: true
    },
    currentValue: {
        type: Number,
        default: 0
    },
    deadline: {
        type: Date
    },
    completed: {
        type: Boolean,
        default: false
    }
}, {
    timestamps: true
});

module.exports = mongoose.model('Goal', goalSchema);
