package com.app.fitspace.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.fitspace.database.UserEntity
import com.app.fitspace.repository.UserRepository
import kotlinx.coroutines.launch

class UserViewModel(
    private val userRepository: UserRepository
) : ViewModel() {

    fun insertUser(user: UserEntity) {
        viewModelScope.launch {
            userRepository.insertUser(user)
        }
    }

    fun updateUser(user: UserEntity) {
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