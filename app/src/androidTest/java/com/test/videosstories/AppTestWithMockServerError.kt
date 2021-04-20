package com.test.videosstories

import android.view.View
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.RootMatchers.withDecorView
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.test.videosstories.common.di.NetworkModule
import com.test.videosstories.common.repository.remote.FakeLocalNetworkService
import com.test.videosstories.common.repository.remote.INetworkService
import com.test.videosstories.common.view.MainActivity
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.ExperimentalCoroutinesApi
import okhttp3.mockwebserver.MockWebServer
import org.hamcrest.Matchers.not
import org.junit.After
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
class AppTestWithMockServerError {

    @get:Rule(order = 0)
    var rule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    var activityScenarioRule: ActivityScenarioRule<MainActivity> = ActivityScenarioRule(MainActivity::class.java)

    private lateinit var mockWebServer: MockWebServer

    private var decorView: View? = null

    @Module
    @InstallIn(SingletonComponent::class)
    abstract class FakeLocalNetworkModule {

        @Singleton
        @Binds
        abstract fun providesNetworkService(networkService: FakeLocalNetworkService): INetworkService
    }

    @Before
    fun setUp() {
        rule.inject()
        mockWebServer = MockWebServer()
        mockWebServer.dispatcher = MockServerDispatcher().ErrorDispatcher()
        mockWebServer.start(8080)

        activityScenarioRule.scenario.onActivity { activity ->
            if (activity != null) {
                decorView = activity.window.decorView
            }
        }
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }

    @Test
    fun runAppWithMockServerError() {
        onView(withText(R.string.no_internet_connection))
            .inRoot(withDecorView(not(decorView)))// Here we use decorView
            .check(matches(isDisplayed()))
    }

}
