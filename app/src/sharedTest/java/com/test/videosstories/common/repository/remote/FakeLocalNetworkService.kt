package com.test.videosstories.common.repository.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject

class FakeLocalNetworkService @Inject constructor() : INetworkService {
    val LOG_TAG = FakeLocalNetworkService::class.simpleName

    private val retrofit = Retrofit.Builder()
        .baseUrl(getUrl())
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    override fun getUrl(): String {
        return "http://localhost:8080/"
    }

    override suspend fun getData(): NetworkItemsCollection {
        val networkAPI = retrofit.create(NetworkAPI::class.java)
        return networkAPI.getAllItems()
    }
}