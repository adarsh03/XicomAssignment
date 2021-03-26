package com.example.xicomassignment.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.xicomassignment.db.model.AuthGenEntity
import com.example.xicomassignment.db.model.ImageListModel
import com.example.xicomassignment.db.model.detailsModel

@Database(entities = [ImageListModel::class,detailsModel::class], version = 3, exportSchema = false)
abstract class MyDatabase : RoomDatabase() {

    abstract val imageListDao: ImageListDao
    abstract val detailModelDao: DetailModelDao

    companion object{
        @Volatile
        private var INSTANCE: MyDatabase? = null

        fun getInstance(context: Context): MyDatabase {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        MyDatabase::class.java,
                        "XicomAssignment"
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}