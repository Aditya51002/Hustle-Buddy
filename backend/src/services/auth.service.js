const User = require('../models/user.model');
const ApiError = require('../utils/ApiError');

class AuthService {
    async registerUser(userData) {
        const { name, email, password } = userData;

        const normalizedEmail = email?.toLowerCase().trim();
        const userExists = await User.findOne({ email: normalizedEmail });
        if (userExists) {
            throw new ApiError(400, 'User already exists');
        }

        const user = await User.create({
            name,
            email: normalizedEmail,
            password
        });

        return user;
    }

    async loginUser(email, password) {
        if (!email || !password) {
            throw new ApiError(400, 'Please provide an email and password');
        }

        const normalizedEmail = email.toLowerCase().trim();
        const user = await User.findOne({ email: normalizedEmail }).select('+password');
        if (!user) {
            throw new ApiError(401, 'Invalid credentials');
        }

        const isMatch = await user.matchPassword(password);
        if (!isMatch) {
            throw new ApiError(401, 'Invalid credentials');
        }

        return user;
    }

    async updateProfile(userId, updateData) {
        if ('password' in updateData || 'role' in updateData) {
            throw new ApiError(400, 'Use dedicated endpoints to update password or role');
        }

        const allowedFields = ['name', 'email', 'avatar', 'preferences'];
        const safeUpdates = {};

        allowedFields.forEach((field) => {
            if (updateData[field] !== undefined) {
                safeUpdates[field] = updateData[field];
            }
        });

        if (safeUpdates.email) {
            safeUpdates.email = safeUpdates.email.toLowerCase().trim();
        }

        const user = await User.findById(userId);
        if (!user) {
            throw new ApiError(404, 'User not found');
        }

        Object.assign(user, safeUpdates);
        await user.save();

        return user;
    }

    async changePassword(userId, currentPassword, newPassword) {
        if (!currentPassword || !newPassword) {
            throw new ApiError(400, 'Current password and new password are required');
        }

        const user = await User.findById(userId).select('+password');
        if (!user) {
            throw new ApiError(404, 'User not found');
        }

        const isMatch = await user.matchPassword(currentPassword);
        if (!isMatch) {
            throw new ApiError(401, 'Current password is incorrect');
        }

        user.password = newPassword;
        await user.save();

        return user;
    }
}

module.exports = new AuthService();
