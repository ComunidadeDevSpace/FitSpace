package com.app.fitspace.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(
    @PrimaryKey
    val id: Long = 0,
    var name: String,
    var email: String,
    var nickname: String,
    var password: String,
    var phone: String,
    var birth: String,
    var gender: String
)
