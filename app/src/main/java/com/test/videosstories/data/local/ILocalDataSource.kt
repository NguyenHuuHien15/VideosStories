package com.test.videosstories.data.local

import com.test.videosstories.data.DataResult
import com.test.videosstories.domain.Video
import com.test.videosstories.domain.WebServiceParams

interface ILocalDataSource {
    suspend fun saveWebServiceParams(webServiceParams: WebServiceParams): DataResult<Boolean>
    suspend fun findWebServiceParams(): DataResult<WebServiceParams>

    suspend fun saveVideos(videos: List<Video>): DataResult<Boolean>
    suspend fun findVideos(): DataResult<List<Video>>
}