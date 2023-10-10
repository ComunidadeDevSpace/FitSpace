package com.app.fitspace.data.repository

import androidx.lifecycle.LiveData
import com.app.fitspace.data.local.UserDao
import com.app.fitspace.data.model.User
import com.app.fitspace.domain.repository.UserRepository

class UserRepositoryImpl(private val userDao: UserDao) : UserRepository {
    override suspend fun insertUser(user: User) {
        userDao.insert(user)
    }

    override suspend fun updateUser(user: User) {
        userDao.update(user)
    }

    override suspend fun getUserByEmail(email: String): LiveData<List<User>> {
        return userDao.getUserByEmail(email)
    }
}