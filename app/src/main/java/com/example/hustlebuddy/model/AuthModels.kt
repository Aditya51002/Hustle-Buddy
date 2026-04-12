package com.example.hustlebuddy.model

data class AuthResponse(
    val success: Boolean,
    val message: String,
    val data: AuthData? = null
)

data class AuthData(
    val user: UserDto,
    val token: String
)

data class UserDto(
    val id: String,
    val name: String,
    val email: String,
    val xp: Int,
    val level: String,
    val studyScore: Int
)

data class LoginRequest(
    val email: String,
    val password: String
)

data class RegisterRequest(
    val name: String,
    val email: String,
    val password: String
)
