package com.smallfat5566.kevinhomework20250325

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.smallfat5566.kevinhomework20250325.ui.activity.MainActivity

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Rule

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {

    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.smallfat5566.kevinhomework20250325", appContext.packageName)
    }

    @Test
    fun demo() {
        Thread.sleep(1000)
        onView(withId(R.id.filterButton))
            .perform(click())
        Thread.sleep(1000)
        onView(withId(R.id.sortDescButton))
            .perform(click())
        Thread.sleep(2000)
        onView(withId(R.id.filterButton))
            .perform(click())
        Thread.sleep(1000)
        onView(withId(R.id.sortAscButton))
            .perform(click())
        Thread.sleep(5000)
    }
}