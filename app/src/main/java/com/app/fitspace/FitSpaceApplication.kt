package com.app.fitspace

import android.app.Application
import androidx.room.Room
import com.app.fitspace.data.local.AppDataBase

class FitSpaceApplication: Application() {

    lateinit var dataBase: AppDataBase
    override fun onCreate() {
        super.onCreate()

        dataBase = Room.databaseBuilder(
            applicationContext,
            AppDataBase::class.java, "user_db"
        ).build()
    }
}