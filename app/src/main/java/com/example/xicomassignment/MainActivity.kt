package com.example.xicomassignment

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.xicomassignment.adapters.ImagesAdapter
import com.example.xicomassignment.databinding.ActivityMainBinding
import com.example.xicomassignment.db.model.Image
import com.example.xicomassignment.db.model.ImageListModel
import com.example.xicomassignment.viewModel.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.image_item.view.*
import java.lang.Exception

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityMainBinding = DataBindingUtil.setContentView(this,
            R.layout.activity_main
        )
        val viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)

        binding.mainViewModel = viewModel
        binding.lifecycleOwner = this

        viewModel.getAllImageData().observe(this, Observer<List<ImageListModel>>{ imgaeData ->

            try {
                for (i in 0..imgaeData.size - 1 ) {

                    val data = imgaeData[i]
                    setUpImagesInRecyclerView(data)
                }
            }catch (e: Exception){
                e.printStackTrace()
            }
        })

    }

    fun setUpImagesInRecyclerView(ImageValues: ImageListModel)
    {
        val listImage = ImageValues.images

        val imgAdapter = ImagesAdapter(listImage!!){ id ->

            if(!id.equals(null)) {
                val intent = Intent(this@MainActivity, SecondPage::class.java)
//                intent.putExtra("id", id)
                startActivity(intent)
            }
        }
        main_recyclerView.adapter = imgAdapter
        main_recyclerView.layoutManager = LinearLayoutManager(this,
            RecyclerView.VERTICAL,false)
        main_recyclerView.setHasFixedSize(true)
    }

}
