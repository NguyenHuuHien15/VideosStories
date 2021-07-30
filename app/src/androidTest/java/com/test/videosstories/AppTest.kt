package com.test.videosstories

import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItem
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.test.videosstories.common.di.NetworkModule
import com.test.videosstories.common.repository.remote.FakeNetworkService
import com.test.videosstories.common.repository.remote.INetworkService
import com.test.videosstories.presentation.MainActivity
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import javax.inject.Singleton

@ExperimentalCoroutinesApi
@UninstallModules(
    NetworkModule::class
)
@RunWith(AndroidJUnit4::class)
@HiltAndroidTest
class AppTest {

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

    @Test
    fun runApp() {
        ActivityScenario.launch(MainActivity::class.java)

        onView(withId(R.id.recy_all_items)).check(matches(isDisplayed()))
        onView(withId(R.id.recy_all_items)).check(matches(atPosition(0, hasDescendant(withText("Story 2")))))
        onView(withId(R.id.recy_all_items)).check(matches(atPosition(1, hasDescendant(withText("Video 1")))))

        // Click on story item at position 0 to open Story details fragment (the order is inversed because of sort by date)
        //onView(withId(R.id.recy_all_items)).perform(actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        onView(withId(R.id.recy_all_items)).perform(actionOnItem<RecyclerView.ViewHolder>(hasDescendant(withText("Story 2")), click()))

        onView(withId(R.id.tv_title)).check(matches(withText("Story 2")))
        onView(withId(R.id.tv_sport_name)).check(matches(withText("Volley")))

        // Back to home fragment
        onView(withId(R.id.img_btn_back)).check(matches(isDisplayed()))
        onView(withId(R.id.img_btn_back)).perform(click())

        // On home fragment
        onView(withId(R.id.recy_all_items)).check(matches(isDisplayed()))
    }

}
