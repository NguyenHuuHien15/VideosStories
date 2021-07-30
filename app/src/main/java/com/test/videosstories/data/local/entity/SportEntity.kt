package com.test.videosstories.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.test.videosstories.domain.Sport

@Entity
class SportEntity constructor(
    @PrimaryKey val id: Int,
    val name: String,
) {
    fun toDomain(): Sport {
        return Sport(id = this.id, name = this.name)
    }
}