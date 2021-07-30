package com.test.videosstories.data.remote

import com.test.videosstories.data.DataResult
import com.test.videosstories.domain.MediaCollection
import com.test.videosstories.domain.WebServiceParams

interface IRemoteDataSource {
    suspend fun getMediaCollection(): DataResult<MediaCollection>
    suspend fun setOrUpdateWebServiceParams(webServiceParams: WebServiceParams)
}