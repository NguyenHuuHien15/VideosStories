package com.test.videosstories.detail.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.test.videosstories.LiveDataTestUtil
import com.test.videosstories.common.repository.ItemsRepo
import com.test.videosstories.common.repository.local.ItemDao
import com.test.videosstories.common.repository.local.ItemDatabase
import com.test.videosstories.common.repository.local.LocalDataSource
import com.test.videosstories.common.repository.remote.*
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.RuleChain
import org.junit.runner.RunWith

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
@HiltAndroidTest
class StoryDetailsViewModelInstrumentedTest {

    @get:Rule
    var rule = RuleChain.outerRule(HiltAndroidRule(this)).around(InstantTaskExecutorRule())

    private lateinit var itemDao: ItemDao
    private lateinit var db: ItemDatabase
    private lateinit var viewModel: StoryDetailsViewModel
    private lateinit var networkService: INetworkService
    private lateinit var itemsRepo: ItemsRepo

    @Before
    fun setUp() {
        db = Room.inMemoryDatabaseBuilder(ApplicationProvider.getApplicationContext(), ItemDatabase::class.java)
            // Allowing main thread queries, just for testing.
            .allowMainThreadQueries()
            .build()
        itemDao = db.itemDao
        networkService = FakeNetworkService()
        itemsRepo = ItemsRepo(RemoteDataSource(networkService), LocalDataSource(itemDao))
        viewModel = StoryDetailsViewModel(itemsRepo)
        runBlockingTest { itemDao.insertAll(listOf(storyEntity)) }
    }

    @After
    fun tearDown() {
        db.close()
    }

    @Test
    fun setStoryId() = runBlockingTest {
        viewModel.setStoryId(story.id)
        assertEquals(story, LiveDataTestUtil.getValue(viewModel.item))
    }
}