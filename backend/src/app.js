const express = require('express');
const cors = require('cors');
const helmet = require('helmet');
const morgan = require('morgan');
const compression = require('compression');
const rateLimit = require('express-rate-limit');
const { errorMiddleware, notFoundMiddleware } = require('./middlewares/error.middleware');

const app = express();

// Security Middlewares
app.use(helmet());
app.use(cors());
app.use(express.json({ limit: '10mb' }));
app.use(express.urlencoded({ extended: true, limit: '10mb' }));
app.use(compression());

// Logging
if (process.env.NODE_ENV === 'development') {
    app.use(morgan('dev'));
}

// Rate Limiting
const limiter = rateLimit({
    windowMs: 15 * 60 * 1000, // 15 minutes
    max: 100 // limit each IP to 100 requests per windowMs
});
app.use('/api', limiter);

// Routes
const authRoutes = require('./routes/auth.routes');
const studyBuddyRoutes = require('./routes/study-buddy.routes');

app.use('/api/v1/auth', authRoutes);
app.use('/api/v1/study-buddy', studyBuddyRoutes);

// Root Route
app.get('/', (req, res) => {
    res.json({
        success: true,
        message: 'Welcome to Study Buddy API',
        version: '1.0.0'
    });
});

// Error Handling
app.use(notFoundMiddleware);
app.use(errorMiddleware);

module.exports = app;
