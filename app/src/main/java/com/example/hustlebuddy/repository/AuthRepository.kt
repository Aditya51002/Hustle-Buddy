package com.example.hustlebuddy.repository

import android.content.Context
import com.example.hustlebuddy.model.AuthResponse
import com.example.hustlebuddy.model.LoginRequest
import com.example.hustlebuddy.model.RegisterRequest
import com.example.hustlebuddy.network.RetrofitClient
import com.example.hustlebuddy.utils.TokenManager
import kotlinx.coroutines.flow.first

class AuthRepository(private val context: Context) {
    private val apiService = RetrofitClient.getInstance(context)
    private val tokenManager = TokenManager(context)

    suspend fun register(request: RegisterRequest): Result<AuthResponse> {
        return try {
            val response = apiService.register(request)
            if (response.isSuccessful && response.body() != null) {
                val authResponse = response.body()!!
                authResponse.data?.token?.let { tokenManager.saveToken(it) }
                Result.success(authResponse)
            } else {
                Result.failure(Exception(response.message()))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun login(request: LoginRequest): Result<AuthResponse> {
        return try {
            val response = apiService.login(request)
            if (response.isSuccessful && response.body() != null) {
                val authResponse = response.body()!!
                authResponse.data?.token?.let { tokenManager.saveToken(it) }
                Result.success(authResponse)
            } else {
                Result.failure(Exception(response.message()))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
    
    suspend fun isLoggedIn(): Boolean {
        return tokenManager.getToken().first() != null
    }

    suspend fun logout() {
        tokenManager.deleteToken()
    }
}
