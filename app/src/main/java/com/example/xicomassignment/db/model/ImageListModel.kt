package com.example.xicomassignment.db.model


import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.xicomassignment.utils.ImageTypeConverters

@TypeConverters(ImageTypeConverters::class)
@Entity(tableName = "image_List")
data class ImageListModel(
    @PrimaryKey(autoGenerate = false)
    val status: String,
    val images: List<Image?>?
)