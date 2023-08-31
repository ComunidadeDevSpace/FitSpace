package com.app.fitspace.presentation.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import com.app.fitspace.data.local.UserGoalsDao
import com.app.fitspace.data.model.UserGoals
import com.app.fitspace.utils.ActionTypeGoals
import com.app.fitspace.utils.GoalsAction
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserGoalsViewModel(
    private val userGoalsDao: UserGoalsDao,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO)
 : ViewModel(){


  
}