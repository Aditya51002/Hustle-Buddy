const express = require('express');
const cors = require('cors');
const helmet = require('helmet');
const morgan = require('morgan');
const { errorMiddleware, notFoundMiddleware } = require('./middlewares/error.middleware');

const app = express();

// Security & Utility Middlewares
app.use(helmet());
app.use(cors());
app.use(express.json());
app.use(express.urlencoded({ extended: true }));

if (process.env.NODE_ENV === 'development') {
    app.use(morgan('dev'));
}

// Routes
const authRoutes = require('./routes/auth.routes');
const studyBuddyRoutes = require('./routes/study-buddy.routes');

app.use('/api/v1/auth', authRoutes);
app.use('/api/v1', studyBuddyRoutes);

// Root Route
app.get('/', (req, res) => {
    res.json({ success: true, message: 'Welcome to Study Buddy API' });
});

// Error Handling
app.use(notFoundMiddleware);
app.use(errorMiddleware);

module.exports = app;
