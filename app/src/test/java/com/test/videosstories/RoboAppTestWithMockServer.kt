package com.test.videosstories

import android.os.Build
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.test.videosstories.common.di.NetworkModule
import com.test.videosstories.common.repository.remote.FakeLocalNetworkService
import com.test.videosstories.common.repository.remote.INetworkService
import com.test.videosstories.presentation.MainActivity
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.HiltTestApplication
import dagger.hilt.android.testing.UninstallModules
import dagger.hilt.components.SingletonComponent
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import javax.inject.Singleton

//@ExperimentalCoroutinesApi
@UninstallModules(
    NetworkModule::class
)
@RunWith(RobolectricTestRunner::class)
//@Config(application = HiltTestApplication::class, qualifiers = "fr-rFR", sdk = [Build.VERSION_CODES.O], manifest = Config.NONE)
@Config(application = HiltTestApplication::class, sdk = [Build.VERSION_CODES.O], manifest = Config.NONE)
@HiltAndroidTest
class RoboAppTestWithMockServer {

    @get:Rule(order = 0)
    var rule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    var activityScenarioRule: ActivityScenarioRule<MainActivity> = ActivityScenarioRule(MainActivity::class.java)

    private lateinit var mockWebServer: MockWebServer

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
        mockWebServer.dispatcher = MockServerDispatcher().RequestDispatcher()
        mockWebServer.start(8080)
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }

    @Test
    fun runAppWithMockServer() {
        activityScenarioRule.scenario.onActivity { activity ->
            val recy = activity.findViewById<RecyclerView>(R.id.recy_all_items)
            assertEquals(View.VISIBLE, recy.visibility)
            recy.getChildAt(0).performClick()
        }
    }

}
