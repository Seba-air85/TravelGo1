package com.example.travelgo.data.repository

import com.example.travelgo.data.api.ApiService
import com.example.travelgo.data.model.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

sealed class ApiResult<out T> {
    data class Success<T>(val data: T) : ApiResult<T>()
    data class Error(val message: String, val code: Int? = null) : ApiResult<Nothing>()
    object Loading : ApiResult<Nothing>()
}

class UserRepository(
    private val apiService: ApiService
) {
    
    suspend fun getUserProfile(token: String): ApiResult<User> = withContext(Dispatchers.IO) {
        try {
            kotlinx.coroutines.delay(1000)
            ApiResult.Success(
                User(
                    id = 1,
                    email = "demo@travelgo.com",
                    name = "Usuario Demo",
                    avatarUrl = null
                )
            )
        } catch (e: Exception) {
            ApiResult.Error(e.message ?: "Error desconocido")
        }
    }
    
    suspend fun updateProfileImage(token: String, imageUri: String): ApiResult<Unit> {
        return ApiResult.Success(Unit)
    }
}
