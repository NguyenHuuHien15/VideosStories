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
) {
    fun toModel(): ItemForView {
        return ItemForView(
            id = this.id,
            title = this.title,
            thumb = this.thumb,
            url = this.url,
            date = this.date,
            sportId = this.sportId,
            sportName = this.sportName,
            views = this.views,
            teaser = this.teaser,
            image = this.image,
            author = this.author,
            isVideo = this.isVideo
        )
    }
}