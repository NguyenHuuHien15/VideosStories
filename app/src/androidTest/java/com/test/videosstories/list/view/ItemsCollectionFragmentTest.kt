package com.test.videosstories.list.view

import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItem
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.test.videosstories.R
import com.test.videosstories.common.di.NetworkModule
import com.test.videosstories.common.repository.remote.FakeNetworkService
import com.test.videosstories.common.repository.remote.INetworkService
import com.test.videosstories.common.repository.remote.story
import com.test.videosstories.common.repository.remote.video
import com.test.videosstories.launchFragmentInHiltContainer
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import javax.inject.Singleton

@ExperimentalCoroutinesApi
@UninstallModules(
    NetworkModule::class
)
@RunWith(AndroidJUnit4::class)
@HiltAndroidTest
class ItemsCollectionFragmentTest {

    @get:Rule
    var rule = HiltAndroidRule(this)

    @Module
    @InstallIn(SingletonComponent::class)
    abstract class TestNetworkModule {

        @Singleton
        @Binds
        abstract fun providesNetworkService(networkService: FakeNetworkService): INetworkService
    }

    @Before
    fun setUp() {
        rule.inject()
    }

    @After
    fun tearDown() {
    }

    @Test
    fun clickStory_navigateToStoryDetailsFragment() {
        // GIVEN - On the home screen
        val navController = mock(NavController::class.java)

        launchFragmentInHiltContainer<ItemsCollectionFragment>(Bundle(), R.style.Theme_VideosStories) {
            Navigation.setViewNavController(this.view!!, navController)
        }

        // WHEN - Click on the story item
        onView(withId(R.id.recy_all_items))
            .perform(actionOnItem<RecyclerView.ViewHolder>(hasDescendant(withText("Story 2")), click()))

        // THEN - Verify that we navigate to story details screen
        val action = ItemsCollectionFragmentDirections.actionItemsCollectionToStoryDetails()
        action.itemId = story.id
        verify(navController).navigate(action)
    }

    @Test
    fun clickVideo_navigateToPlayerVideoFragment() {
        // GIVEN - On the home screen
        val navController = mock(NavController::class.java)

        launchFragmentInHiltContainer<ItemsCollectionFragment>(Bundle(), R.style.Theme_VideosStories) {
            Navigation.setViewNavController(this.view!!, navController)
        }

        // WHEN - Click on the story item
        onView(withId(R.id.recy_all_items))
            .perform(actionOnItem<RecyclerView.ViewHolder>(hasDescendant(withText("Video 1")), click()))

        // THEN - Verify that we navigate to player video screen
        val action = ItemsCollectionFragmentDirections.actionItemsCollectionToPlayerVideo()
        action.urlVideo = video.url.toString()
        verify(navController).navigate(action)
    }
}