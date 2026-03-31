/**
 * Constants used throughout the application
 */

const ROLES = {
    USER: 'user',
    ADMIN: 'admin'
};

const PRIORITY = {
    LOW: 'LOW',
    MEDIUM: 'MEDIUM',
    HIGH: 'HIGH'
};

const GOAL_TYPES = {
    STUDY_HOURS: 'study-hours',
    TASKS_COMPLETED: 'tasks-completed',
    QUIZZES_TAKEN: 'quizzes-taken',
    CUSTOM: 'custom'
};

const SESSION_MODES = {
    POMODORO: 'pomodoro',
    SHORT_BREAK: 'short-break',
    LONG_BREAK: 'long-break',
    CUSTOM: 'custom'
};

module.exports = {
    ROLES,
    PRIORITY,
    GOAL_TYPES,
    SESSION_MODES
};
