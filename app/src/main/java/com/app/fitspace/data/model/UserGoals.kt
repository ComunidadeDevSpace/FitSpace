package com.app.fitspace.data.model

import android.widget.Spinner
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class UserGoals(
    @PrimaryKey(autoGenerate = true)
    val id:Int = 0,
    val weight: Double,
    val height: Double,
    val upperBody: Double,
    val neck: Double,
    val hips: Double,
    val waist: Double,
    val rightArm: Double,
    val leftArm: Double,
    val rightThigh: Double,
    val leftThigh:Double,
    val rightCalv:Double,
    val leftCalv:Double,
    val goal:String,
    val weeklyExercise: String
)
