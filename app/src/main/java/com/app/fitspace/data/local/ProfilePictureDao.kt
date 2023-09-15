package com.app.fitspace.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface ProfilePictureDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProfilePicture(picture: ProfilePicture)

    @Query("SELECT * FROM profilepicture")
    fun getPicture(): LiveData<ProfilePicture>
}