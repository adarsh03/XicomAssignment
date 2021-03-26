package com.example.xicomassignment.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.xicomassignment.R
import com.example.xicomassignment.db.model.Image
import kotlinx.android.synthetic.main.image_item.view.*
import java.lang.Exception


class ImagesAdapter(val imageList: List<Image?>?, val itemClick: (String) -> Unit) :
    RecyclerView.Adapter<ImagesAdapter.ViewHolder>() {

    override fun onCreateViewHolder(p0: ViewGroup, viewType: Int): ViewHolder {
        val v: View = LayoutInflater.from(p0.context)
            .inflate(R.layout.image_item,p0,false)

        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
    try {

            Glide.with(holder.itemView.context).load(imageList!!.get(position)!!.xtImage)
                .into(holder.project_images)

        holder.project_images.setOnClickListener {
            itemClick(imageList.get(position)!!.id!!)
        }

        } catch (e: Exception){
        e.printStackTrace()
    }


    }
    override fun getItemCount(): Int {

        return imageList!!.size
    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val project_images = itemView.image_icon_id
    }

}
