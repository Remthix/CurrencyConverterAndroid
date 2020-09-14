package com.sdremthix.currencyconverter.ui.activities

import android.content.pm.ActivityInfo
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.doesNotExist
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.filters.LargeTest
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.sdremthix.currencyconverter.R
import org.hamcrest.CoreMatchers.containsString
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * The Main view component testing suite.
 */
@RunWith(AndroidJUnit4ClassRunner::class)
@LargeTest
class MainActivityTest : BaseActivity() {

    @get:Rule
    val mMainActivityScenarioRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun applicationSupportsOnlyPortraitScreenOrientation() {
        mMainActivityScenarioRule.scenario.onActivity {
            assertTrue(it.requestedOrientation == ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)
        }
    }

    @Test
    fun defaultProjectSetupTextIsShowing_fail() {
        onView(withText("Hello world!")).check(doesNotExist())
    }

    @Test
    fun currencySpinnerIsShowing() {
        onView(withId(R.id.spinner_currency)).check(matches(isDisplayed()))
    }

    @Test
    fun currencySpinnerIsDisplayingTestData() {
        onView(withId(R.id.spinner_currency)).check(matches(withSpinnerText(containsString("EUR"))))
    }
}