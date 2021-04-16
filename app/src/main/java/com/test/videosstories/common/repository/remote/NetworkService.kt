package com.test.videosstories.common.repository.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject

class NetworkService @Inject constructor() : INetworkService{
    val LOG_TAG = NetworkService::class.simpleName

    private val retrofit = Retrofit.Builder()
        .baseUrl(getUrl())
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    override fun getUrl(): String {
        return "https://extendsclass.com"
    }

    override suspend fun getData(): NetworkItemsCollection {
        val networkAPI = retrofit.create(NetworkAPI::class.java)
        return networkAPI.getAllItems()
    }
}