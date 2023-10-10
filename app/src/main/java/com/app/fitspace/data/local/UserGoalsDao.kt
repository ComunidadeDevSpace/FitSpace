package com.app.fitspace.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Update
import com.app.fitspace.data.model.UserGoals

@Dao
interface UserGoalsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGoals(userGoals: UserGoals)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateGoals(userGoals: UserGoals)

}