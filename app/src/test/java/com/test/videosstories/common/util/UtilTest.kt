package com.test.videosstories.common.util

import com.test.videosstories.common.repository.local.entity.ItemEntity
import com.test.videosstories.common.repository.remote.model.NetworkItemsCollection
import com.test.videosstories.common.repository.remote.model.NetworkSport
import com.test.videosstories.common.repository.remote.model.NetworkStory
import com.test.videosstories.common.repository.remote.model.NetworkVideo
import com.test.videosstories.common.model.ItemForView
import org.apache.commons.lang3.StringUtils
import org.junit.Before
import org.junit.Test

import org.junit.Assert.*

class UtilTest {
    private lateinit var videoEntity: ItemEntity
    private lateinit var storyEntity: ItemEntity

    private lateinit var video: ItemForView
    private lateinit var story: ItemForView

    private lateinit var sportFootball : NetworkSport
    private lateinit var sportVolley : NetworkSport
    private lateinit var networkVideo: NetworkVideo
    private lateinit var networkStory: NetworkStory
    private lateinit var networkItemsCollection: NetworkItemsCollection

    @Before
    fun setUp() {
        videoEntity = ItemEntity(
            1, "Video 1", "https://abc.com/vid.jpg", "https://abc.com/vid.mp4",
            11.1, 111, "Football", 1000, StringUtils.EMPTY, StringUtils.EMPTY, StringUtils.EMPTY, true
        )

        storyEntity = ItemEntity(
            2, "Story 2", StringUtils.EMPTY, StringUtils.EMPTY, 22.2, 222, "Volley",
            null, "Volley teaser content", "https://abc.com/volley.jpg", "David", false
        )

        video = ItemForView(
            1, "Video 1", "https://abc.com/vid.jpg", "https://abc.com/vid.mp4",
            11.1, 111, "Football", 1000, StringUtils.EMPTY, StringUtils.EMPTY, StringUtils.EMPTY, true
        )

        story = ItemForView(
            2, "Story 2", StringUtils.EMPTY, StringUtils.EMPTY, 22.2, 222, "Volley",
            null, "Volley teaser content", "https://abc.com/volley.jpg", "David", false
        )

        sportFootball = NetworkSport(111, "Football")
        sportVolley = NetworkSport(222, "Volley")
        networkVideo = NetworkVideo(1, "Video 1", "https://abc.com/vid.jpg", "https://abc.com/vid.mp4", 11.1, sportFootball, 1000)
        networkStory = NetworkStory(2, "Story 2", "Volley teaser content", "https://abc.com/volley.jpg", 22.2, "David", sportVolley)
        networkItemsCollection = NetworkItemsCollection(listOf(networkVideo), listOf(networkStory))
    }

    @Test
    fun networkToEntities() {
        val entities = networkToEntities(networkItemsCollection)
        assertEquals(listOf(videoEntity, storyEntity), entities)
    }

    @Test
    fun toModels() {
        var entitiesList : List<ItemEntity>? = null
        var itemsList = toModels(entitiesList)
        assertTrue(itemsList.isEmpty())

        entitiesList = emptyList()
        itemsList = toModels(entitiesList)
        assertTrue(itemsList.isEmpty())

        entitiesList = listOf(videoEntity, storyEntity)
        itemsList = toModels(entitiesList)
        assertEquals(listOf(video, story), itemsList)
    }

    @Test
    fun sortByDate() {
        var entryList : List<ItemForView>? = null
        var resultList = sortByDate(entryList)
        assertTrue(resultList.isEmpty())

        entryList = emptyList()
        resultList = sortByDate(entryList)
        assertTrue(resultList.isEmpty())

        entryList = listOf(video, story)
        resultList = sortByDate(entryList)
        assertEquals(listOf(story, video), resultList)

        entryList = listOf(story, video)
        resultList = sortByDate(entryList)
        assertEquals(listOf(story, video), resultList)
    }
}