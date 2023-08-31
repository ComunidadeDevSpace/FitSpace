package com.app.fitspace

import android.app.Application
import androidx.room.Room
import com.app.fitspace.data.local.AppDatabase

class FitSpaceApplication: Application() {

    lateinit var dataBase: AppDatabase
    override fun onCreate() {
        super.onCreate()

        dataBase = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "user_db"
        ).build()
    }
}