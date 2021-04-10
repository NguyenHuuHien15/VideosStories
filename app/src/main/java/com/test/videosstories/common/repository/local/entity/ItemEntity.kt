package com.test.videosstories.common.repository.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

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