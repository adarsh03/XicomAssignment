package com.example.xicomassignment.db.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "otp_generate")
data class AuthGenEntity(
    @PrimaryKey(autoGenerate = false)
    val phone_otp_hash: String,
    val new_user: Boolean
)