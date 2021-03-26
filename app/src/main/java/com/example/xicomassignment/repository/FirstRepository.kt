package com.example.xicomassignment.repository

import android.app.Application
import android.content.SharedPreferences
import android.util.Log
import androidx.lifecycle.LiveData
import com.example.xicomassignment.db.MyDatabase
import com.example.xicomassignment.db.model.ImageListModel
import com.example.xicomassignment.db.model.detailsModel
import com.example.xicomassignment.network.ApiServices
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response
import java.io.File

class FirstRepository(application: Application) {
    val database: MyDatabase
    val apiServices = ApiServices()

    init {
        database = MyDatabase.getInstance(
            application.applicationContext
        )
    }
//user_data: HashMap<String, String>
    suspend fun getImageListApi(id: Int): Response<ImageListModel> {
         val response = apiServices.ImageApi(id)
        Log.d("hhddh",response.message())

        when (response.isSuccessful) {
            true -> {
                withContext(Dispatchers.IO) {
                    database.imageListDao.delete()
                    database.imageListDao.insert(response.body()!!)
                }
            }
        }

        return response
    }

    suspend fun setAllData(first_name: String, last_name: String, email: String, phone: String,
                           user_img: File
    ): Response<detailsModel> {

        val first_name = RequestBody.create(MediaType.parse("text/plain"), first_name)
        val last_name = RequestBody.create(MediaType.parse("text/plain"), last_name)
        val email = RequestBody.create(MediaType.parse("text/plain"), email)
        val phone = RequestBody.create(MediaType.parse("text/plain"), phone)

        val user_img = MultipartBody.Part.createFormData("user_image", user_img.name.toString(),
            RequestBody.create(MediaType.parse("image/jpeg"), user_img))

        val response = apiServices.SetDataApi(first_name,last_name,email,phone,user_img)
        Log.d("hhddh",response.message())

        when (response.isSuccessful) {
            true -> {
                withContext(Dispatchers.IO) {
                    database.detailModelDao.delete()
                    database.detailModelDao.insert(response.body()!!)
                }
            }
        }

        return response
    }


    fun getImagesFromDatabase(): LiveData<List<ImageListModel>> {
        return database.imageListDao.getAllData()
    }



}
