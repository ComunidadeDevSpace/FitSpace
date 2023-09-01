package com.app.fitspace.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.fitspace.data.model.User
import com.app.fitspace.data.repository.UserRepositoryImpl
import kotlinx.coroutines.launch

class UserViewModel(
    private val userRepositoryImpl: UserRepositoryImpl
) : ViewModel() {

    fun insertUser(user: User) {
        viewModelScope.launch {
            userRepositoryImpl.insertUser(user)
        }
    }

    fun updateUser(user: User) {
        viewModelScope.launch {
            userRepositoryImpl.updateUser(user)
        }
    }

    fun getUserByEmail(email: String) {
        viewModelScope.launch {
            userRepositoryImpl.getUserByEmail(email)
        }
    }
}