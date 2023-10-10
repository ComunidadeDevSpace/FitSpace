package com.app.fitspace.utils

import com.app.fitspace.data.model.UserGoals
import java.io.Serializable

enum class ActionTypeGoals {
    INSERT,
    UPDATE
}

data class GoalsAction(
    val userGoals: UserGoals?,
    val actionType: String
) : Serializable