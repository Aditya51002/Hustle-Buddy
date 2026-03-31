const mongoose = require('mongoose');

const subjectSchema = new mongoose.Schema({
    userId: {
        type: mongoose.Schema.Types.ObjectId,
        ref: 'User',
        required: true
    },
    subjectName: {
        type: String,
        required: [true, 'Please add a subject name'],
        trim: true
    },
    color: {
        type: Number,
        required: true
    },
    icon: {
        type: String,
        default: 'book'
    },
    progress: {
        type: Number,
        default: 0
    },
    targetHours: {
        type: Number,
        default: 0
    },
    totalStudyMinutes: {
        type: Number,
        default: 0
    }
}, {
    timestamps: true
});

module.exports = mongoose.model('Subject', subjectSchema);
