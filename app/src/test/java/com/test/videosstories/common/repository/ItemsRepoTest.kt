package com.test.videosstories.common.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import com.test.videosstories.LiveDataTestUtil
import com.test.videosstories.common.repository.local.ItemDao
import com.test.videosstories.common.repository.local.ItemDatabase
import com.test.videosstories.common.repository.local.entity.ItemEntity
import com.test.videosstories.common.repository.remote.INetworkService
import com.test.videosstories.list.model.ItemForView
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.apache.commons.lang3.StringUtils
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when` as whenever

class ItemsRepoTest {
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var db: ItemDatabase
    private lateinit var itemDao: ItemDao
    private lateinit var networkService: INetworkService
    private lateinit var itemsRepo: ItemsRepo

    private lateinit var videoEntity: ItemEntity
    private lateinit var storyEntity: ItemEntity

    private lateinit var video: ItemForView
    private lateinit var story: ItemForView

    @Before
    fun setUp() {
        db = mock(ItemDatabase::class.java)
        itemDao = mock(ItemDao::class.java)
        networkService = mock(INetworkService::class.java)
        itemsRepo = ItemsRepo(networkService, db)

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
    }

    @Test
    fun getAndSaveNetworkItemsToDB() {
        // nothing to test
    }

    @Test
    fun getAllItemsFromDB() {
        whenever(db.itemDao).thenReturn(itemDao)
        whenever(itemDao.getItems()).thenReturn(MutableLiveData(listOf(videoEntity, storyEntity)))

        val allItemsFromDB = itemsRepo.getAllItemsFromDB()

        assertEquals(listOf(video, story), LiveDataTestUtil.getValue(allItemsFromDB))
    }

    @ExperimentalCoroutinesApi
    @Test
    fun getItemFromDB() = runBlockingTest {
        whenever(db.itemDao).thenReturn(itemDao)
        whenever(itemDao.getItem(videoEntity.id)).thenReturn(videoEntity)

        val item = itemsRepo.getItemFromDB(videoEntity.id)

        assertEquals(video, item)
    }
}