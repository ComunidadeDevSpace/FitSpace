package com.app.fitspace.presentation.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.app.fitspace.data.db.AppDatabase
import com.app.fitspace.data.model.user.User
import com.app.fitspace.domain.repository.UserRepository
import com.app.fitspace.data.repository.UserRepositoryImpl
import kotlinx.coroutines.launch

class UserViewModel(
    application: Application
) : AndroidViewModel(application) {

    private val userRepository: UserRepository
    init {
        val userDao = AppDatabase.getInstance(application).userDao()
        userRepository = UserRepositoryImpl(userDao)
    }

    fun insertUser(user: User) {
        viewModelScope.launch {
            userRepository.insertUser(user)
        }
    }

    fun updateUser(user: User) {
        viewModelScope.launch {
            userRepository.updateUser(user)
        }
    }

    fun getUserByEmail(email: String) {
        viewModelScope.launch {
            userRepository.getUserByEmail(email)
        }
    }
}