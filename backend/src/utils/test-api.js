require('dotenv').config();
const mongoose = require('mongoose');
const User = require('../models/user.model');
const studyBuddyService = require('../services/study-buddy.service');

const runTest = async () => {
    try {
        await mongoose.connect(process.env.MONGODB_URI);
        console.log('Connected to MongoDB for testing...');

        // 1. Clean up test user
        await User.deleteMany({ email: 'test@example.com' });
        
        // 2. Create User
        console.log('Testing User Creation...');
        const user = await User.create({
            name: 'Test User',
            email: 'test@example.com',
            password: 'password123'
        });
        console.log('✅ User Created:', user.name);

        // 3. Test Subject Service
        console.log('Testing Subject Creation...');
        const subject = await studyBuddyService.createSubject(user._id, {
            subjectName: 'Mobile Development',
            color: 0xFF4285F4,
            icon: 'android'
        });
        console.log('✅ Subject Created:', subject.subjectName);

        // 4. Test Task Service
        console.log('Testing Task Creation...');
        const task = await studyBuddyService.createTask(user._id, {
            subjectId: subject._id,
            title: 'Build Backend',
            deadline: new Date(),
            priority: 'HIGH'
        });
        console.log('✅ Task Created:', task.title);

        // 5. Test Analytics
        console.log('Testing Analytics Summary...');
        const analytics = await studyBuddyService.getAnalyticsSummary(user._id);
        console.log('✅ Analytics Score:', analytics.studyScore);

        // 6. Test Gamification
        console.log('Testing Gamification (XP Add)...');
        const updatedUser = await studyBuddyService.addXP(user._id, 100);
        console.log('✅ User XP Updated:', updatedUser.xp);

        console.log('\n🌟 ALL CORE SERVICES ARE WORKING PROPERLY 🌟');
        process.exit(0);
    } catch (error) {
        console.error('❌ Test Failed:', error);
        process.exit(1);
    }
};

runTest();
