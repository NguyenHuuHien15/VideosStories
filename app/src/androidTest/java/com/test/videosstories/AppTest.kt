package com.test.videosstories

import android.view.View
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItem
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.matcher.BoundedMatcher
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.junit.Before
import org.junit.BeforeClass
import org.junit.Rule
import org.junit.Test
import org.junit.rules.RuleChain
import org.junit.runner.RunWith

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
@HiltAndroidTest
class AppTest {

    @get:Rule
    var rule = RuleChain.outerRule(HiltAndroidRule(this))

    /*@ExperimentalCoroutinesApi
    @Before
    fun clearDB() = runBlockingTest {
        InstrumentationRegistry.getInstrumentation().targetContext.deleteDatabase("items")
    }*/

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
