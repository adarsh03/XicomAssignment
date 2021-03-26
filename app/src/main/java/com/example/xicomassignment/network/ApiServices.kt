package  com.example.xicomassignment.network


import com.example.xicomassignment.db.model.AuthGenEntity
import com.example.xicomassignment.db.model.ImageListModel
import com.example.xicomassignment.db.model.detailsModel
import com.google.gson.Gson
import okhttp3.MultipartBody
import okhttp3.OkHttpClient
import okhttp3.RequestBody
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*


private const val BASE_URL = "http://dev1.xicom.us"

private val logger = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

val okHttp = OkHttpClient.Builder()
    .addInterceptor(logger)

interface ApiServices {

    @Multipart
    @POST("/xttest/getdata.php")
    suspend fun ImageApi(
        @Part("user_id") user_id: Int
        ): Response<ImageListModel>

    @Multipart
    @POST("/xttest/savedata.php")
    suspend fun SetDataApi(
        @Part("first_name") first_name: RequestBody,
        @Part("last_name") last_name: RequestBody,
        @Part("email") email: RequestBody,
        @Part("phone") phone: RequestBody,
        @Part user_image: MultipartBody.Part
    ): Response<detailsModel>


    companion object {
        val gson = Gson()
        operator fun invoke(): ApiServices {
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(okHttp.build())
                .build()
                .create(ApiServices::class.java)
        }
    }
}

