package com.app.fitspace.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.app.fitspace.data.model.UserGoals

@Dao
interface UserGoalsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGoals(userGoals: UserGoals)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateGoals(userGoals: UserGoals)

    @Query("SELECT weight FROM user_goals ORDER BY id DESC LIMIT 1")
    fun getLastUserWeight(): Double

    @Query("SELECT height FROM user_goals ORDER BY id DESC LIMIT 1")
    fun getLastUserHeight(): Double
}