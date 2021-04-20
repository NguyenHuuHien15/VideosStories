package com.test.videosstories.common.repository.local.entity

import com.test.videosstories.common.model.ItemForView
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class ItemEntityTest {

    private lateinit var videoEntity: ItemEntity
    private lateinit var storyEntity: ItemEntity

    private lateinit var video: ItemForView
    private lateinit var story: ItemForView

    @Before
    fun setup() {
        videoEntity = ItemEntity(
            1, "Video 1", "https://abc.com/vid.jpg", "https://abc.com/vid.mp4",
            11.1, 111, "Football", 1000, null, null, "John", true
        )

        storyEntity = ItemEntity(
            2, "Story 2", null, null, 22.2, 222, "Volley",
            null, "Volley teaser content", "https://abc.com/volley.jpg", "David", false
        )

        video = ItemForView(
            1, "Video 1", "https://abc.com/vid.jpg", "https://abc.com/vid.mp4",
            11.1, 111, "Football", 1000, null, null, "John", true
        )

        story = ItemForView(
            2, "Story 2", null, null, 22.2, 222, "Volley",
            null, "Volley teaser content", "https://abc.com/volley.jpg", "David", false
        )
    }

    @Test
    fun toModel() {
        assertEquals(video, videoEntity.toModel())
        assertEquals(story, storyEntity.toModel())
    }
}