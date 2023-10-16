package com.app.fitspace.data.model.user

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(
    @PrimaryKey (autoGenerate = true)
    val id: Long,
    var name: String,
    var email: String,
    var nickname: String,
    var password: String,
    var phone: String,
    var birth: String,
    var gender: String
)
