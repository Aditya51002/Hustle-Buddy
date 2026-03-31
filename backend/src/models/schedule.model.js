const mongoose = require('mongoose');

const scheduleSchema = new mongoose.Schema({
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
    date: {
        type: String, // e.g., 'Monday', '2023-11-20'
        required: true
    },
    startTime: {
        type: String,
        required: true
    },
    endTime: {
        type: String
    },
    duration: {
        type: String
    },
    notes: {
        type: String
    },
    status: {
        type: String,
        enum: ['scheduled', 'completed', 'cancelled'],
        default: 'scheduled'
    }
}, {
    timestamps: true
});

module.exports = mongoose.model('Schedule', scheduleSchema);
