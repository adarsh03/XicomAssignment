package com.example.xicomassignment.db.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_Detail")
data class detailsModel(
    @PrimaryKey(autoGenerate = false)
    val user: Int,
    val first_name: String,
    val last_name: String,
    val email: String,
    val phone: String,
    val user_image: String
)