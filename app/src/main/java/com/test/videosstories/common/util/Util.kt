package com.test.videosstories.common.util

import com.test.videosstories.common.repository.local.entity.ItemEntity
import com.test.videosstories.common.repository.remote.model.NetworkItemsCollection
import com.test.videosstories.common.model.ItemForView
import com.test.videosstories.domain.Video
import org.apache.commons.lang3.StringUtils

fun networkToEntities(networkItemsCollection: NetworkItemsCollection): List<ItemEntity> {
    val list = mutableListOf<ItemEntity>()
    val videos = networkItemsCollection.videos
    if (videos != null) {
        for (item in videos) {
            list.add(
                ItemEntity(
                    item.id, item.title, item.thumb, item.url, item.date, item.sport.id, item.sport.name, item.views,
                    StringUtils.EMPTY, StringUtils.EMPTY, StringUtils.EMPTY, true
                )
            )
        }
    }

    val stories = networkItemsCollection.stories
    if (stories != null) {
        for (item in stories) {
            list.add(
                ItemEntity(
                    item.id, item.title, StringUtils.EMPTY, StringUtils.EMPTY, item.date, item.sport.id, item.sport.name,
                    null, item.teaser, item.image, item.author, false
                )
            )
        }
    }

    return list
}

fun toModels(list: List<ItemEntity>?): List<ItemForView> {
    if (list == null || list.isEmpty()) return emptyList()
    return list.map {
        it.toModel()
    }
}

fun sortByDate(list: List<ItemForView>?): List<ItemForView> {
    if (list == null || list.isEmpty()) return emptyList()
    return list.toMutableList().sortedByDescending { it.date }
}

fun sortVideosByDate(list: List<Video>?): List<Video> {
    if (list == null || list.isEmpty()) return emptyList()
    return list.toMutableList().sortedByDescending { it.date }
}