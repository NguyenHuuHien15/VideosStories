package com.test.videosstories.common.repository.source.remote

import com.squareup.moshi.JsonClass
import com.test.videosstories.common.repository.source.local.entity.ItemEntity

@JsonClass(generateAdapter = true)
data class NetworkItemsContainer(val items: List<NetworkItem>) {
    fun toEntities(): List<ItemEntity> {
        return items.map {
            ItemEntity(id = it.id, title = it.title)
        }
    }
}

