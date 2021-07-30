package hien.android.commonapi.presentation

import android.content.DialogInterface
import android.view.View
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.RootMatchers
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import hien.android.commonapi.msgAppNotFound
import hien.android.commonapi.presentation.activity.ActivityForTest
import org.hamcrest.Matchers
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class GraphicUtilTest {

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
    fun toastLong() {
        ActivityScenario.launch(ActivityForTest::class.java)

        activityScenarioRule.scenario.onActivity { activity ->
            activity?.runOnUiThread {
                hien.android.commonapi.presentation.toastLong(activity, msgAppNotFound)
            }
        }

        // Vérification l'affichage du msg sur Toast
        onView(withText(msgAppNotFound))
            .inRoot(RootMatchers.withDecorView(Matchers.not(decorView)))
            .check(matches(isDisplayed()))
    }

    @Test
    fun toastShort() {
        ActivityScenario.launch(ActivityForTest::class.java)

        activityScenarioRule.scenario.onActivity { activity ->
            activity?.runOnUiThread {
                hien.android.commonapi.presentation.toastShort(activity, msgAppNotFound)
            }
        }

        // Vérification l'affichage du msg sur Toast
        onView(withText(msgAppNotFound))
            .inRoot(RootMatchers.withDecorView(Matchers.not(decorView)))
            .check(matches(isDisplayed()))
    }

    @Test
    fun showBlockingAlertDialog() {
        ActivityScenario.launch(ActivityForTest::class.java)

        activityScenarioRule.scenario.onActivity { activity ->
            activity?.runOnUiThread {
                val alertDialog = hien.android.commonapi.presentation.showBlockingAlertDialog(activity, "Titre", "Msg", "OK", 0, false) {
                    it.dismiss()
                }

                assertTrue(alertDialog.isShowing)

                val button = alertDialog.getButton(DialogInterface.BUTTON_POSITIVE)
                assertEquals("OK", button.text)
                button.performClick()

                assertFalse(alertDialog.isShowing)
            }
        }
    }
}