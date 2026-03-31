const jwt = require('jsonwebtoken');
const asyncHandler = require('../utils/asyncHandler');
const ApiError = require('../utils/ApiError');
const User = require('../models/user.model');

const protect = asyncHandler(async (req, res, next) => {
    let token;

    if (
        req.headers.authorization &&
        req.headers.authorization.startsWith('Bearer')
    ) {
        token = req.headers.authorization.split(' ')[1];
    }

    if (!token) {
        throw new ApiError(401, 'Not authorized to access this route');
    }

    try {
        // Verify token
        const decoded = jwt.verify(token, process.env.JWT_SECRET);

        req.user = await User.findById(decoded.id);
        
        if (!req.user) {
            throw new ApiError(404, 'User not found with this id');
        }

        next();
    } catch (err) {
        throw new ApiError(401, 'Not authorized to access this route');
    }
});

// Grant access to specific roles
const authorize = (...roles) => {
    return (req, res, next) => {
        if (!roles.includes(req.user.role)) {
            throw new ApiError(403, `User role ${req.user.role} is not authorized to access this route`);
        }
        next();
    };
};

module.exports = { protect, authorize };
