package com.app.fitspace.presentation.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.app.fitspace.data.local.AppDataBase
import com.app.fitspace.data.model.User
import com.app.fitspace.domain.repository.UserRepository
import com.app.fitspace.data.repository.UserRepositoryImpl
import kotlinx.coroutines.launch

class UserViewModel(
    application: Application
) : AndroidViewModel(application) {

    private val userRepository: UserRepository
    init {
        val userDao = AppDataBase.getInstance(application).userDao()
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