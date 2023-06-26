package com.app.fitspace.repository

import com.app.fitspace.database.DataBase
import com.app.fitspace.database.UserEntity

class UserRepository(private val db: DataBase) {

     suspend fun insertUser(user: UserEntity) = db.userDao().insert(user)

    suspend fun updateUser(user: UserEntity) = db.userDao().update(user)

    fun getUserByEmail(email: String) = db.userDao().getUserByEmail(email)
}