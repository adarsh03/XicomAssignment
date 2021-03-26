package com.example.xicomassignment.bindingAdapter

import android.content.Context
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.example.xicomassignment.R

@BindingAdapter("app:imageUrl" )
fun imageSet(view: ImageView, url: String?){
    Glide.with(view)
        .load(url)
        .placeholder(R.drawable.img)
        .into(view)
}

