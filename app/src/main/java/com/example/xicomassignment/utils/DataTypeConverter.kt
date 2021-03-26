package com.example.xicomassignment.utils

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.util.*

class ImageTypeConverters {

    @TypeConverter
    fun stringToSomeObjectList(data: String?): List<com.example.xicomassignment.db.model.Image?>? {
        if (data == null) {
            return Collections.emptyList()
        }

        val listType = object : TypeToken<List<com.example.xicomassignment.db.model.Image?>?>() {}.type
        return Gson().fromJson(data, listType)
    }

    @TypeConverter
    fun someObjectListToString(someObjects: List<com.example.xicomassignment.db.model.Image?>?): String {
        return Gson().toJson(someObjects)
    }

}
