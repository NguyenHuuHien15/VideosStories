package com.test.videosstories.list.view

import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.test.videosstories.R
import com.test.videosstories.common.repository.ItemsRepo
import com.test.videosstories.common.repository.local.ItemDao
import com.test.videosstories.common.repository.local.ItemDatabase
import com.test.videosstories.common.repository.remote.FakeNetworkService
import com.test.videosstories.common.repository.remote.INetworkService
import com.test.videosstories.launchFragmentInHiltContainer
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.mock

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
@HiltAndroidTest
class ItemsCollectionFragmentTest {

    private lateinit var itemDao: ItemDao
    private lateinit var db: ItemDatabase
    private lateinit var networkService: INetworkService
    private lateinit var itemsRepo: ItemsRepo

    @get:Rule
    var rule = HiltAndroidRule(this)

    @Before
    fun setUp() {
        rule.inject()
        db = Room.inMemoryDatabaseBuilder(ApplicationProvider.getApplicationContext(), ItemDatabase::class.java)
            // Allowing main thread queries, just for testing.
            .allowMainThreadQueries()
            .build()
        itemDao = db.itemDao
        networkService = FakeNetworkService()
        itemsRepo = ItemsRepo(networkService, db)
    }

    @After
    fun tearDown() {
        db.close()
    }

    @Test
    fun test() {
        // GIVEN - On the home screen
        val navController = mock(NavController::class.java)

        launchFragmentInHiltContainer<ItemsCollectionFragment>(Bundle(), R.style.Theme_VideosStories) {
            Navigation.setViewNavController(this.view!!, navController)
        }
    }
}