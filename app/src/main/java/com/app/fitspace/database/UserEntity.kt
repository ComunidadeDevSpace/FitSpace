package com.app.fitspace.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class UserEntity (
    @PrimaryKey
     val name: String,
     val email: String,
     val nickname: String,
     val password: String,
     val phone: String,
     val birth: String,
     val gender: String
)

// :Serializable - usa ou não?
