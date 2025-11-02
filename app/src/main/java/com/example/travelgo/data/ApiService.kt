package com.example.travelgo.data

import com.example.travelgo.model.User

class ApiService {
    suspend fun getMe(): User {
        return User(
            id = "123",
            name = "Juan Pérez",
            email = "juan@example.com",
            avatarUrl = "https://i.pravatar.cc/150?img=3"
        )
    }
}