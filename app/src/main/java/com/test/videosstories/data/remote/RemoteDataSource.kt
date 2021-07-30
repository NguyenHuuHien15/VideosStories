package com.test.videosstories.data.remote

import com.test.videosstories.data.DataResult
import com.test.videosstories.domain.MediaCollection
import com.test.videosstories.domain.WebServiceParams
import javax.inject.Inject

class RemoteDataSource @Inject constructor(private val webService: WebService) : IRemoteDataSource {
    override suspend fun getMediaCollection(): DataResult<MediaCollection> {
       return webService.getMediaCollection()
    }

    override suspend fun setOrUpdateWebServiceParams(webServiceParams: WebServiceParams) {
        webService.setOrUpdateWebServiceParams(webServiceParams)
    }
}