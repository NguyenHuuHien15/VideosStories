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
import com.test.videosstories.common.repository.remote.FakeLocalNetworkService
import com.test.videosstories.common.repository.remote.INetworkService
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.ExperimentalCoroutinesApi
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@ExperimentalCoroutinesApi
@UninstallModules(
    NetworkModule::class
)
@RunWith(AndroidJUnit4::class)
@HiltAndroidTest
class AppTestWithMockServer {

    @get:Rule
    var rule = HiltAndroidRule(this)

    protected lateinit var mockWebServer: MockWebServer

    @Module
    @InstallIn(SingletonComponent::class)
    abstract class FakeLocalNetworkModule {

        @Binds
        abstract fun providesNetworkService(networkService: FakeLocalNetworkService): INetworkService
    }

    @Before
    fun init() {
        rule.inject()
        mockWebServer = MockWebServer()
        mockWebServer.dispatcher = MockServerDispatcher().RequestDispatcher()
        mockWebServer.start(8080)
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }

    @Test
    fun runApp() {
        ActivityScenario.launch(MainActivity::class.java)

        onView(withId(R.id.recy_all_items)).check(matches(isDisplayed()))
        onView(withId(R.id.recy_all_items)).check(matches(atPosition(0, hasDescendant(withText("CHRONIQUE FRITSCH")))))
        onView(withId(R.id.recy_all_items)).check(matches(atPosition(1, hasDescendant(withText("Il n'en fallait pas plus au Real pour relancer le feuilleton Mbappé")))))

        // Click on story item at position 0 to open Story details fragment (the order is inversed because of sort by date)
        onView(withId(R.id.recy_all_items)).perform(actionOnItem<RecyclerView.ViewHolder>(hasDescendant(withText("CHRONIQUE FRITSCH")), click()))

        // Back to home fragment
        onView(withId(R.id.img_btn_back)).check(matches(isDisplayed()))
        onView(withId(R.id.img_btn_back)).perform(click())

        // On home fragment
        onView(withId(R.id.recy_all_items)).check(matches(isDisplayed()))
    }

}
