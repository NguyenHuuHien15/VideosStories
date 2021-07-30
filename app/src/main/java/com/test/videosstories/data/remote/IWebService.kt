package com.test.videosstories.data.remote

import com.test.videosstories.data.DataResult
import com.test.videosstories.domain.MediaCollection

interface IWebService {
    suspend fun getMediaCollection(): DataResult<MediaCollection>
}