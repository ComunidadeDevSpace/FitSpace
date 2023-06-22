package com.app.fitspace.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [UserEntity::class], version = 1)
abstract class DataBase: RoomDatabase() {
    abstract fun userDao(): UserDao

}