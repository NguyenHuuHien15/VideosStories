package com.test.videosstories.common.repository.remote

import retrofit2.http.GET

interface NetworkAPI {

    @GET("/api/json-storage/bin/edfefba")
    suspend fun getAllItems(): NetworkItemsCollection

}