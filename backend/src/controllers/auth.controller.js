const asyncHandler = require('../utils/asyncHandler');
const ApiResponse = require('../utils/ApiResponse');
const authService = require('../services/auth.service');

/**
 * @desc    Register user
 * @route   POST /api/v1/auth/register
 * @access  Public
 */
const register = asyncHandler(async (req, res) => {
    const user = await authService.registerUser(req.body);
    const token = user.getSignedJwtToken();

    res.status(201).json(
        new ApiResponse(201, { user, token }, "User registered successfully")
    );
});

/**
 * @desc    Login user
 * @route   POST /api/v1/auth/login
 * @access  Public
 */
const login = asyncHandler(async (req, res) => {
    const { email, password } = req.body;
    const user = await authService.loginUser(email, password);
    const token = user.getSignedJwtToken();

    res.status(200).json(
        new ApiResponse(200, { user, token }, "Login successful")
    );
});

/**
 * @desc    Get current user
 * @route   GET /api/v1/auth/me
 * @access  Private
 */
const getMe = asyncHandler(async (req, res) => {
    res.status(200).json(
        new ApiResponse(200, req.user, "User profile fetched")
    );
});

/**
 * @desc    Update user profile
 * @route   PUT /api/v1/auth/profile
 * @access  Private
 */
const updateProfile = asyncHandler(async (req, res) => {
    const user = await authService.updateProfile(req.user.id, req.body);
    res.status(200).json(
        new ApiResponse(200, user, "Profile updated successfully")
    );
});

/**
 * @desc    Change password
 * @route   PUT /api/v1/auth/change-password
 * @access  Private
 */
const changePassword = asyncHandler(async (req, res) => {
    const { currentPassword, newPassword } = req.body;
    await authService.changePassword(req.user.id, currentPassword, newPassword);
    res.status(200).json(
        new ApiResponse(200, null, "Password changed successfully")
    );
});

module.exports = {
    register,
    login,
    getMe,
    updateProfile,
    changePassword
};
