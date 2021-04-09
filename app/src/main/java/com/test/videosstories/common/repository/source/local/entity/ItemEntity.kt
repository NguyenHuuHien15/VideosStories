package com.test.videosstories.common.repository.source.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ItemEntity constructor(
    @PrimaryKey val id: Int,
    val title: String
)