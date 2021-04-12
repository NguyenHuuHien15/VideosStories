package com.test.videosstories.common.repository.local

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.test.videosstories.LiveDataInstrumentedTestUtil
import com.test.videosstories.common.repository.local.entity.ItemEntity
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
class ItemDaoTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var itemDao: ItemDao
    private lateinit var db: ItemDatabase

    private lateinit var videoEntity: ItemEntity
    private lateinit var storyEntity: ItemEntity

    @Before
    fun createDb() {
        db = Room.inMemoryDatabaseBuilder(ApplicationProvider.getApplicationContext(), ItemDatabase::class.java)
            // Allowing main thread queries, just for testing.
            .allowMainThreadQueries()
            .build()
        itemDao = db.itemDao

        videoEntity = ItemEntity(
            1, "Video 1", "https://abc.com/vid.jpg", "https://abc.com/vid.mp4",
            11.1, 111, "Football", 1000, null, null, "John", true
        )

        storyEntity = ItemEntity(
            2, "Story 2", null, null, 22.2, 222, "Volley",
            null, "Volley teaser content", "https://abc.com/volley.jpg", "David", false
        )
    }

    @After
    fun closeDb() {
        db.close()
    }

    @Test
    fun insertAllAndGetAll() = runBlockingTest {
        val entryList = mutableListOf(videoEntity, storyEntity)
        itemDao.insertAll(entryList)

        val listInDB = itemDao.getItems()
        assertEquals(LiveDataInstrumentedTestUtil.getValue(listInDB), entryList)
    }

    @Test
    fun insertAllAndGetOne() = runBlockingTest {
        val entryList = mutableListOf(videoEntity, storyEntity)
        itemDao.insertAll(entryList)

        var itemInDB = itemDao.getItem(videoEntity.id)
        assertEquals(itemInDB, videoEntity)

        itemInDB = itemDao.getItem(storyEntity.id)
        assertEquals(itemInDB, storyEntity)
    }
}
