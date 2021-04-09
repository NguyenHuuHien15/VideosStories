package com.test.videosstories.common.repository.remote

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.test.videosstories.common.repository.ItemsRepo
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/*interface NetworkService {
    @GET("videos")
    suspend fun getData(): NetworkItemsContainer
}*/

const val BASE_URL = "https://extendsclass.com"

object NetworkCaller {
    val LOG_TAG = ItemsRepo::class.simpleName

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    suspend fun getData(): NetworkItemsCollection {
        val networkAPI = retrofit.create(NetworkAPI::class.java)
        return networkAPI.getAllItems()
    }
}