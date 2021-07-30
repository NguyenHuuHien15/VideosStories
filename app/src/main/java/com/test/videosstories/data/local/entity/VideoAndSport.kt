package com.test.videosstories.data.local.entity

import androidx.room.Embedded
import androidx.room.Relation
import com.test.videosstories.domain.Video

data class VideoAndSport(
    @Embedded val sport: SportEntity,
    @Relation(parentColumn = "id", entityColumn = "sportId")
    val videos: List<VideoEntity> = emptyList()
) {
    fun toDomain(): List<Video> {
        val vids = mutableListOf<Video>()
        for (video in videos) {
            vids.add(Video(video.id, video.title, video.thumb, video.url, video.date, sport.toDomain(), video.views))
        }
        return vids
    }
}