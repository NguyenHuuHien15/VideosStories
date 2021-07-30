package com.test.videosstories.data.remote

import com.test.videosstories.domain.MediaCollection
import retrofit2.Response
import retrofit2.http.GET

interface IWebServiceAPI {

    //@GET("/api/json-storage/bin/edfefba")
    @GET("edfefba")
    suspend fun getMediaCollection(): Response<MediaCollection>
}