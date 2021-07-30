package com.test.videosstories.data.local.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    foreignKeys = [
        ForeignKey(entity = SportEntity::class, parentColumns = ["id"], childColumns = ["sportId"])
    ],
    indices = [Index("sportId")]
)

class VideoEntity constructor(
    @PrimaryKey val id: Int,
    val title: String,
    val thumb: String,
    val url: String,
    val date: Double,
    val views: Long,
    val sportId : Int
) {
}