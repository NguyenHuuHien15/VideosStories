package com.test.videosstories.common.repository.source.remote

import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

interface NetworkService {
    @GET("videos")
    suspend fun getData(): NetworkItemsContainer
}

object NetworkCaller {
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://extendsclass.com/api/json-storage/bin/edfefba/")
        .addConverterFactory(MoshiConverterFactory.create())
        .build()

    val remoteItems = retrofit.create(NetworkService::class.java)
}