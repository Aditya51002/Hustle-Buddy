const ApiError = require('../utils/ApiError');

const errorMiddleware = (err, req, res, next) => {
    let error = err;

    if (!(error instanceof ApiError)) {
        const statusCode = error.statusCode || (error.name === 'ValidationError' ? 400 : 500);
        const message = error.message || 'Internal Server Error';
        error = new ApiError(statusCode, message, error?.errors || [], err.stack);
    }

    const response = {
        success: false,
        message: error.message,
        ...(process.env.NODE_ENV === 'development' ? { stack: error.stack } : {}),
        errors: error.errors
    };

    res.status(error.statusCode).json(response);
};

const notFoundMiddleware = (req, res, next) => {
    const error = new ApiError(404, `Not Found - ${req.originalUrl}`);
    next(error);
};

module.exports = {
    errorMiddleware,
    notFoundMiddleware
};
