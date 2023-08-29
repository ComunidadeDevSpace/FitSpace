package com.app.fitspace.data.model.user

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(
    @PrimaryKey
    val name: String,
    val email: String,
    val nickname: String,
    val password: String,
    val phone: String,
    val birth: String,
    val gender: String
)
