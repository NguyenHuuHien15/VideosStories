package com.test.videosstories.common.repository.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ItemEntity constructor(
    @PrimaryKey val id: Int,
    val title: String
)