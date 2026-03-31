const User = require('../models/user.model');
const ApiError = require('../utils/ApiError');

class AuthService {
    async registerUser(userData) {
        const { name, email, password } = userData;
        
        const userExists = await User.findOne({ email });
        if (userExists) {
            throw new ApiError(400, 'User already exists');
        }

        const user = await User.create({
            name,
            email,
            password
        });

        return user;
    }

    async loginUser(email, password) {
        if (!email || !password) {
            throw new ApiError(400, 'Please provide an email and password');
        }

        const user = await User.findOne({ email }).select('+password');
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
        const user = await User.findByIdAndUpdate(userId, updateData, {
            new: true,
            runValidators: true
        });
        return user;
    }

    async changePassword(userId, currentPassword, newPassword) {
        const user = await User.findById(userId).select('+password');
        
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
