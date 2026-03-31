const express = require('express');
const { register, login, getMe, updateProfile, changePassword } = require('../controllers/auth.controller');
const { protect } = require('../middlewares/auth.middleware');
const { registerValidator, loginValidator } = require('../validators/auth.validator');
const validate = require('../middlewares/validate.middleware');

const router = express.Router();

router.post('/register', registerValidator, validate, register);
router.post('/login', loginValidator, validate, login);

// Private routes
router.use(protect);
router.get('/me', getMe);
router.put('/profile', updateProfile);
router.put('/change-password', changePassword);

module.exports = router;
