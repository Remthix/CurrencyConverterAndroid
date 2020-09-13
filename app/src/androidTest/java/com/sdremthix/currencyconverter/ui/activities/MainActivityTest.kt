package com.sdremthix.currencyconverter.ui.activities

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.doesNotExist
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * The Main view component testing suite.
 */
@RunWith(AndroidJUnit4ClassRunner::class)
class MainActivityTest : BaseActivity() {

    @get:Rule
    val mMainActivityScenarioRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun defaultProjectSetupTextIsShowing_pass() {
        onView(withText("Hello World!")).check(matches(isDisplayed()))
    }

    @Test
    fun defaultProjectSetupTextIsShowing_fail() {
        onView(withText("Hello world!")).check(doesNotExist())
    }
}