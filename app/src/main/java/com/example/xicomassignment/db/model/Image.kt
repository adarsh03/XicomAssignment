package com.example.xicomassignment.db.model


import com.google.gson.annotations.SerializedName

data class Image(
    val id: String?,
    @SerializedName("xt_image")
    val xtImage: String?
)