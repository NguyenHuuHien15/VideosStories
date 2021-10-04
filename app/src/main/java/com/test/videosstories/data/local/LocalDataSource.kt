package com.test.videosstories.data.local

import android.content.Context
import com.test.videosstories.WEB_SERVICE_PARAMS_KEY
import com.test.videosstories.data.DataResult
import com.test.videosstories.data.local.entity.SportEntity
import com.test.videosstories.data.local.entity.VideoEntity
import com.test.videosstories.domain.Video
import com.test.videosstories.domain.WebServiceParams
import dagger.hilt.android.qualifiers.ApplicationContext
import hien.android.commonapi.readFromPrefs
import hien.android.commonapi.saveToPrefs
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalDataSource @Inject constructor(
    @ApplicationContext private val context: Context,
    private val db: MyDatabase
) : ILocalDataSource {
    override suspend fun saveWebServiceParams(webServiceParams: WebServiceParams): DataResult<Boolean> {
        context.saveToPrefs(webServiceParams, WEB_SERVICE_PARAMS_KEY)
        // A revoir ou maj plus tard
        return DataResult.Success(true)
    }

    override suspend fun findWebServiceParams(): DataResult<WebServiceParams> {
        val webServiceParams = context.readFromPrefs(WEB_SERVICE_PARAMS_KEY, WebServiceParams::class.java)
        return DataResult.Success(webServiceParams as WebServiceParams)
    }

    override suspend fun saveVideos(videos: List<Video>): DataResult<Boolean> {
        for (vid in videos) {
            val sportEntity = SportEntity(vid.sport.id, vid.sport.name)
            db.sportDao.insert(sportEntity)

            val vidEntity = VideoEntity(
                id = vid.id,
                title = vid.title,
                thumb = vid.thumb,
                url = vid.url,
                date = vid.date,
                views = vid.views,
                sportId = vid.sport.id
            )
            db.videoDao.insert(vidEntity)
        }

        return DataResult.Success(true)
    }

    override suspend fun findVideos(): DataResult<List<Video>> {
        val videoDao = db.videoDao
        val videosAndSports = videoDao.getVideosAndSports()
        val vids = mutableListOf<Video>()
        for (videoAndSport in videosAndSports) {
            vids.addAll(videoAndSport.toDomain())
        }
        return DataResult.Success(vids)
    }
}