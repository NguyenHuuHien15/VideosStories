package hien.android.commonapi

import android.view.View
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.RootMatchers
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import hien.android.commonapi.launchOtherApp
import hien.android.commonapi.presentation.activity.ActivityForTest
import hien.android.commonapi.presentation.toastShort
import hien.android.commonapi.appIdNotFound
import hien.android.commonapi.msgAppNotFound
import org.hamcrest.Matchers
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class AppUtilTest {

    @get:Rule
    var activityScenarioRule: ActivityScenarioRule<ActivityForTest> = ActivityScenarioRule(ActivityForTest::class.java)

    private var decorView: View? = null

    @Before
    fun setUp() {
        activityScenarioRule.scenario.onActivity { activity ->
            if (activity != null) {
                decorView = activity.window.decorView
            }
        }
    }

    @Test
    fun launchOtherAppNotFound() {
        ActivityScenario.launch(ActivityForTest::class.java)

        activityScenarioRule.scenario.onActivity { activity ->
            activity?.runOnUiThread {
                launchOtherApp(activity, appIdNotFound) {
                    toastShort(activity, msgAppNotFound)
                }
            }
        }

        // Vérifier que le msg "App introuvalbe" est affiché par Toast
        Espresso.onView(ViewMatchers.withText(msgAppNotFound))
            .inRoot(RootMatchers.withDecorView(Matchers.not(decorView)))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun launchOtherAppFound() {
        ActivityScenario.launch(ActivityForTest::class.java)

        activityScenarioRule.scenario.onActivity { activity ->
            activity?.runOnUiThread {
                launchOtherApp(activity, "com.google.android.youtube") {
                    toastShort(activity, msgAppNotFound)
                }
            }
        }

        // Resume the activity
        ActivityScenario.launch(ActivityForTest::class.java)

        // Vérifier que le msg n'existe pas parce que l'app Youtube est bien lancée
        Espresso.onView(ViewMatchers.withText(msgAppNotFound)).check(ViewAssertions.doesNotExist())
    }
}