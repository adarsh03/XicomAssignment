package com.example.xicomassignment.viewModel

import android.app.Application
import android.view.View
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.xicomassignment.db.model.ImageListModel
import com.example.xicomassignment.repository.FirstRepository
import com.example.xicomassignment.utils.Coroutines
import com.google.android.material.snackbar.Snackbar
import java.io.File

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private var repository = FirstRepository(application)


    var first_name = MutableLiveData<String>()
    var last_name = MutableLiveData<String>()
    var email = MutableLiveData<String>()
    var phone = MutableLiveData<String>()
    var headerImage = MutableLiveData<String>()
    var ImgValue: String? = null

    val ImageSuccessful = MutableLiveData<Boolean>().apply { postValue(false) }


    init {
        var userAccountOldData: List<ImageListModel>? = null

        Coroutines.io {
            repository.getImageListApi(108)

            try {
//                userAccountOldData = repository.getImagesFromDatabase()
//                userAccountOldData!!.forEach {
//                    ImgValue = it.images
//                }
            } catch (e: Exception) {
                e.printStackTrace()
            }

        }

    }

    fun getAllImageData(): LiveData<List<ImageListModel>> {
        return repository.getImagesFromDatabase()
    }


    fun setDataInDB(view: View) {


        if (first_name.value.isNullOrEmpty()) {
            Snackbar.make(view, "Please enter Account Holder Name", Snackbar.LENGTH_LONG).show()
        } else if (last_name.value.isNullOrEmpty()) {
            Snackbar.make(view, "Please enter Bank Name", Snackbar.LENGTH_LONG).show()
        } else if (email.value.isNullOrEmpty()) {
            Snackbar.make(view, "Please enter Branch Name", Snackbar.LENGTH_LONG).show()
        } else if (phone.value.isNullOrEmpty()) {
            Snackbar.make(view, "Please enter Account Number", Snackbar.LENGTH_LONG).show()
        } else if (ImageSuccessful.value == false) {
            Snackbar.make(view, "Please add Cheque Image", Snackbar.LENGTH_LONG).show()

        } else {

            Coroutines.io{

                val imageData = File(headerImage.value!!)


               val response = repository.setAllData(
                    first_name.value!!,
                    last_name.value!!,
                    email.value!!,
                    phone.value!!,
                    imageData )

            }
        }

        }
    }