const { body } = require('express-validator');

const subjectValidator = [
    body('subjectName').notEmpty().withMessage('Subject name is required'),
    body('color').isNumeric().withMessage('Color must be a number'),
];

const taskValidator = [
    body('subjectId').isMongoId().withMessage('Valid subject ID is required'),
    body('title').notEmpty().withMessage('Title is required'),
    body('deadline').isISO8601().withMessage('Valid deadline date is required'),
    body('priority').optional().isIn(['LOW', 'MEDIUM', 'HIGH']).withMessage('Priority must be LOW, MEDIUM, or HIGH'),
];

const scheduleValidator = [
    body('subjectId').isMongoId().withMessage('Valid subject ID is required'),
    body('date').notEmpty().withMessage('Date is required'),
    body('startTime').notEmpty().withMessage('Start time is required'),
];

const sessionValidator = [
    body('subjectId').isMongoId().withMessage('Valid subject ID is required'),
    body('actualMinutes').isNumeric().withMessage('Actual minutes must be a number'),
    body('mode').optional().isIn(['pomodoro', 'short-break', 'long-break', 'custom']),
];

const goalValidator = [
    body('title').notEmpty().withMessage('Title is required'),
    body('type').isIn(['study-hours', 'tasks-completed', 'quizzes-taken', 'custom']),
    body('targetValue').isNumeric().withMessage('Target value must be a number'),
];

const reminderValidator = [
    body('title').notEmpty().withMessage('Title is required'),
    body('reminderTime').isISO8601().withMessage('Valid reminder time is required'),
];

module.exports = {
    subjectValidator,
    taskValidator,
    scheduleValidator,
    sessionValidator,
    goalValidator,
    reminderValidator
};
