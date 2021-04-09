package com.test.videosstories.common.repository.source.remote

import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

interface NetworkService {
    @GET("devbytes")
    suspend fun getData(): NetworkItemsContainer
}

object NetworkCaller {
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://android-kotlin-fun-mars-server.appspot.com/")
        .addConverterFactory(MoshiConverterFactory.create())
        .build()

    val remoteItems = retrofit.create(NetworkService::class.java)
}