require('dotenv').config();
const mongoose = require('mongoose');
const Quiz = require('../models/quiz.model');

const quizzes = [
    {
        subject: 'Computer Science',
        title: 'DSA Basics',
        difficulty: 'easy',
        questions: [
            {
                question: 'What is the time complexity of binary search?',
                options: ['O(n)', 'O(log n)', 'O(n^2)', 'O(1)'],
                correctAnswerIndex: 1,
                explanation: 'Binary search divides the search space in half each step, leading to logarithmic time.'
            },
            {
                question: 'Which data structure uses LIFO?',
                options: ['Queue', 'Stack', 'Linked List', 'Tree'],
                correctAnswerIndex: 1,
                explanation: 'Stack follows Last-In-First-Out (LIFO) principle.'
            }
        ]
    },
    {
        subject: 'Android',
        title: 'Jetpack Compose Fundamentals',
        difficulty: 'medium',
        questions: [
            {
                question: 'What is the primary way to define UI in Jetpack Compose?',
                options: ['XML Files', 'Composable Functions', 'Activities', 'Fragments'],
                correctAnswerIndex: 1,
                explanation: 'In Compose, UI is built using @Composable functions.'
            }
        ]
    }
];

const seedData = async () => {
    try {
        await mongoose.connect(process.env.MONGODB_URI);
        console.log('Connected to MongoDB for seeding...');

        await Quiz.deleteMany();
        await Quiz.insertMany(quizzes);

        console.log('✅ Data Seeded Successfully');
        process.exit();
    } catch (error) {
        console.error('❌ Error Seeding Data:', error);
        process.exit(1);
    }
};

seedData();
