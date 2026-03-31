const mongoose = require('mongoose');

const reminderSchema = new mongoose.Schema({
    userId: {
        type: mongoose.Schema.Types.ObjectId,
        ref: 'User',
        required: true
    },
    title: {
        type: String,
        required: [true, 'Please add a title'],
        trim: true
    },
    reminderTime: {
        type: Date,
        required: true
    },
    repeatType: {
        type: String,
        enum: ['none', 'daily', 'weekly', 'monthly'],
        default: 'none'
    },
    enabled: {
        type: Boolean,
        default: true
    }
}, {
    timestamps: true
});

module.exports = mongoose.model('Reminder', reminderSchema);
