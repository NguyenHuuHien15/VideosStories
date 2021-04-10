package com.test.videosstories.common.repository.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.test.videosstories.list.model.ItemForView

@Entity(tableName = "item_entity_table")
data class ItemEntity constructor(
    @PrimaryKey val id: Int,
    val title: String?,
    val thumb: String?,
    val url: String?,
    val date: Double?,
    val sportId: Int,
    val sportName: String,
    val views: Long?,
    val teaser: String?,
    val image: String?,
    val author: String?,
    val isVideo: Boolean
)

fun List<ItemEntity>.toModels(): List<ItemForView> {
    return map {
        ItemForView(
            id = it.id,
            title = it.title,
            thumb = it.thumb,
            url = it.url,
            date = it.date,
            sportId = it.sportId,
            sportName = it.sportName,
            views = it.views,
            teaser = it.teaser,
            image = it.image,
            author = it.author,
            isVideo = it.isVideo
        )
    }
}