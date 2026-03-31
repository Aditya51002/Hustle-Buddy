const mongoose = require('mongoose');

const questionSchema = new mongoose.Schema({
    question: {
        type: String,
        required: true
    },
    options: {
        type: [String],
        required: true,
        validate: [v => v.length >= 2, 'Minimum 2 options required']
    },
    correctAnswerIndex: {
        type: Number,
        required: true
    },
    explanation: {
        type: String
    }
});

const quizSchema = new mongoose.Schema({
    subject: {
        type: String,
        required: true
    },
    title: {
        type: String,
        required: true,
        trim: true
    },
    difficulty: {
        type: String,
        enum: ['easy', 'medium', 'hard'],
        default: 'medium'
    },
    questions: [questionSchema]
}, {
    timestamps: true
});

module.exports = mongoose.model('Quiz', quizSchema);
