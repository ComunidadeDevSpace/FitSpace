package com.app.fitspace.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ProfilePicture (
    @PrimaryKey
    val id:Int,
    val profilePicture: ByteArray,
    val width: Int,
    val height: Int )
