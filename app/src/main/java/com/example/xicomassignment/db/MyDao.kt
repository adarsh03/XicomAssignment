package  com.example.xicomassignment.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.xicomassignment.db.model.ImageListModel
import com.example.xicomassignment.db.model.detailsModel

// DAO CAN BE ABSTRACT CLASS OR INTERFACE
interface BaseDao<T> {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(obj: T)
}

@Dao
abstract class ImageListDao: BaseDao<ImageListModel> {
    @Query("DELETE FROM image_List")
    abstract fun delete()

    @Query("SELECT * from image_List")
    @JvmSuppressWildcards
    abstract fun getAllData(): LiveData<List<ImageListModel>>
}

@Dao
interface DetailModelDao: BaseDao<detailsModel> {

    @Query("DELETE FROM user_Detail")
    fun delete()

    @Query("SELECT * from user_Detail")
    @JvmSuppressWildcards
    fun getAllDetailData(): List<detailsModel>
}