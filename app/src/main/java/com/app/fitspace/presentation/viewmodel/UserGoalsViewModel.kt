package com.app.fitspace.presentation.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import com.app.fitspace.FitSpaceApplication
import com.app.fitspace.data.local.UserGoalsDao
import com.app.fitspace.data.model.UserGoals
import com.app.fitspace.utils.ActionTypeGoals
import com.app.fitspace.utils.GoalsAction
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserGoalsViewModel(
    private val userGoalsDao: UserGoalsDao)
    : ViewModel(){


    fun execute(goalsAction: GoalsAction){
        when (goalsAction.actionType){
            ActionTypeGoals.INSERT.name -> insert(goalsAction.userGoals!!)
            ActionTypeGoals.UPDATE.name -> update(goalsAction.userGoals!!)
        }
    }


    private fun insert(userGoals: UserGoals){
        viewModelScope.launch {
            userGoalsDao.insertGoals(userGoals)
        }
    }

    private fun update(userGoals: UserGoals){
        viewModelScope.launch {
            userGoalsDao.updateGoals(userGoals)
        }
    }



    companion object {
        fun getVmFactory(application: Application): ViewModelProvider.Factory {
            val dataBaseInstance = (application as FitSpaceApplication).getAppDataBase()
            val dao  = dataBaseInstance.userGoalsDao()

            return object : ViewModelProvider.Factory{
                override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {
                    return UserGoalsViewModel(dao) as T
                }
            }
        }
    }


}