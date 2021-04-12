package com.test.videosstories.common.repository.remote

import com.test.videosstories.common.repository.ItemsRepo
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

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