package com.app.fitspace.domain.repository

import androidx.lifecycle.LiveData
import com.app.fitspace.data.model.User

interface UserRepository {
    suspend fun insertUser(user: User)
    suspend fun updateUser(user: User)
    suspend fun getUserByEmail(email: String): LiveData<List<User>>
}