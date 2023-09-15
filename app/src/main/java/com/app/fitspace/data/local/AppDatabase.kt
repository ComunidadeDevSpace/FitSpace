package com.app.fitspace.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.app.fitspace.data.model.User
import com.app.fitspace.data.model.UserGoals

@Database(entities = [User::class, UserGoals::class, ProfilePicture::class], version = 2)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun userGoalsDao(): UserGoalsDao

    abstract fun ProfilePictureDao():ProfilePictureDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext, AppDatabase::class.java, "user_db"
                    ).build()
                }
                return instance
            }
        }
    }
}