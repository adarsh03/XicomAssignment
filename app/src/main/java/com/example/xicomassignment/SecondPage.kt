package com.example.xicomassignment

import android.content.Context
import android.content.Intent
import android.database.Cursor
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.ImageView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.example.xicomassignment.adapters.ImagesAdapter
import com.example.xicomassignment.databinding.ActivityMainBinding
import com.example.xicomassignment.databinding.ActivitySecondPageBinding
import com.example.xicomassignment.db.model.ImageListModel
import com.example.xicomassignment.utils.Coroutines
import com.example.xicomassignment.viewModel.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*
import java.io.ByteArrayOutputStream
import java.lang.Exception

class SecondPage : AppCompatActivity() {

    lateinit var viewModel: MainViewModel
    var imageView: ImageView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivitySecondPageBinding = DataBindingUtil.setContentView(this,
            R.layout.activity_second_page
        )
         viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)

        binding.secondViewModel = viewModel
        binding.lifecycleOwner = this


        viewModel.getAllImageData().observe(this, Observer<List<ImageListModel>>{ imgaeData ->

            try {
                for (i in 0..imgaeData.size - 1 ) {

                    val data = imgaeData[i]
//                    setUpImagesInRecyclerView(data)
                }
            }catch (e: Exception){
                e.printStackTrace()
            }
        })

    }

    fun setimageData(){

        Coroutines.main {
            try {

                val value = viewModel.ImgValue
                if (value != null) {
                    Glide.with(this).asBitmap().load(value).into(object : CustomTarget<Bitmap>() {
                        override fun onResourceReady(
                            resource: Bitmap,
                            transition: com.bumptech.glide.request.transition.Transition<in Bitmap>?
                        ) {
                            imageView!!.setImageBitmap(resource)
                            val tempUri1 = getImageUri(applicationContext, resource)
                            viewModel.ImageSuccessful.value = true
                            viewModel.headerImage.value = getRealPathFromURI(tempUri1!!)

                        }

                        override fun onLoadCleared(placeholder: Drawable?) {
                        }
                    })
                }

            } catch (e: java.lang.Exception) {
                e.printStackTrace()
            }
        }

    }
    fun getImageUri(inContext: Context, inImage: Bitmap): Uri? {
        val bytes = ByteArrayOutputStream()
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes)
        val path: String =
            MediaStore.Images.Media.insertImage(inContext.getContentResolver(), inImage, "Title", null)
        return Uri.parse(path)
    }

    private fun getRealPathFromURI(contentURI: Uri): String? {
        val result: String?
        val cursor: Cursor? = getContentResolver().query(contentURI, null, null, null, null)
        if (cursor == null) { // Source is Dropbox or other similar local file path
            result = contentURI.getPath()
        } else {
            cursor.moveToFirst()
            val idx: Int = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA)
            result = cursor.getString(idx)
            cursor.close()
        }
        return result
    }

}
