package com.example.travelgo.data

import android.content.Context
import com.example.travelgo.model.User

class UserPrefs(context: Context) {
    private val prefs = context.getSharedPreferences("user_prefs", Context.MODE_PRIVATE)

    fun saveProfileImageUri(uri: String) {
        prefs.edit().putString("profile_image_uri", uri).apply()
    }

    fun getProfileImageUri(): String? {
        return prefs.getString("profile_image_uri", null)
    }

    fun saveUser(user: User) {
        prefs.edit().apply {
            putString("id", user.id)
            putString("name", user.name)
            putString("email", user.email)
            putString("avatarUrl", user.avatarUrl)
            apply()
        }
    }

    fun getUser(): User? {
        val id = prefs.getString("id", null) ?: return null
        val name = prefs.getString("name", null)
        val email = prefs.getString("email", null)
        val avatarUrl = prefs.getString("avatarUrl", null)
        return User(id, name, email, avatarUrl)
    }

    fun clearUser() {
        prefs.edit().clear().apply()
    }

    fun saveToken(token: String) {
        prefs.edit().putString("auth_token", token).apply()
    }

    fun getToken(): String? {
        return prefs.getString("auth_token", null)
    }
}
