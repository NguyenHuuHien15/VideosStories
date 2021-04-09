package com.test.videosstories.common.repository.source.remote

import com.test.videosstories.common.model.Item
import retrofit2.Call
import retrofit2.http.GET

interface NetworkAPI {
    @GET("/api/json-storage/bin/edfefba")
    fun getAllItems(): Call<Item>
}