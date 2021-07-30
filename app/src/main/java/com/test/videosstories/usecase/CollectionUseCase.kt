package com.test.videosstories.usecase

import android.util.Log
import com.test.videosstories.data.DataResult
import com.test.videosstories.data.local.ILocalDataSource
import com.test.videosstories.data.remote.IRemoteDataSource
import com.test.videosstories.domain.MediaCollection
import com.test.videosstories.domain.Video
import com.test.videosstories.domain.WebServiceParams
import hien.android.commonapi.toFormattedString
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CollectionUseCase @Inject constructor(
    private val remoteDataSource: IRemoteDataSource,
    private val localDataSource: ILocalDataSource
) {
    suspend fun saveWebServiceParams(webServiceParams: WebServiceParams) {
        wrapEspressoIdlingResource {
            localDataSource.saveWebServiceParams(webServiceParams)
        }
    }

    suspend fun setOrUpdateWebServiceParams() {
        wrapEspressoIdlingResource {
            val wsParams = localDataSource.findWebServiceParams()
            if (wsParams is DataResult.Success) {
                remoteDataSource.setOrUpdateWebServiceParams(wsParams.data)
            }
        }
    }

    suspend fun getMediaCollection(): DataResult<MediaCollection> {
        wrapEspressoIdlingResource {
            return remoteDataSource.getMediaCollection()
        }
    }

    suspend fun saveVideos(videos: List<Video>) {
        wrapEspressoIdlingResource {
            localDataSource.saveVideos(videos)
        }
    }

    suspend fun findVideos(): DataResult<List<Video>> {
        wrapEspressoIdlingResource {
            val foundVideos = localDataSource.findVideos()
            //Log.d("HienHien", "On findVideos : " + foundVideos.toFormattedString())
            if (foundVideos is DataResult.Success) {
                Log.d("HienHien", "On findVideos : " + foundVideos.data.size)
                for (vid in foundVideos.data) {
                    Log.d("HienHien", "     On findVideos : " + vid.toFormattedString())
                }
            }
            return foundVideos
        }
    }
}