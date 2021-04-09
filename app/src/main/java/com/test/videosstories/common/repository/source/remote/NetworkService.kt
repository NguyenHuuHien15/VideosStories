package com.test.videosstories.common.repository.source.remote

import android.util.Log
import com.test.videosstories.common.model.Item
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

const val LOG_TAG = "NetworkCaller"

object NetworkCaller {
    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    //val remoteItems = retrofit.create(NetworkService::class.java)

    fun create(retrofitInterfaceClass: Class<NetworkAPI?>?): NetworkAPI? {
        return null
    }

    suspend fun getData() {
        val networkAPI = retrofit.create(NetworkAPI::class.java)
        val call: Call<Item> = networkAPI.getAllItems()

        call.enqueue(object : Callback<Item> {
            override fun onResponse(call: Call<Item>, response: Response<Item>) {
                Log.d(LOG_TAG, "onResponse: " + response.body())
            }

            override fun onFailure(call: Call<Item>, t: Throwable) {
            }
        })
    }
}