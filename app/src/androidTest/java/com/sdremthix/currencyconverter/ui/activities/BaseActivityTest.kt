package com.sdremthix.currencyconverter.ui.activities

import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import org.junit.Rule
import org.junit.runner.RunWith

/**
 * The Base view component testing suite.
 */
@RunWith(AndroidJUnit4ClassRunner::class)
class BaseActivityTest {

    @get:Rule
    val mBaseActivityScenarioRule = ActivityScenarioRule(BaseActivity::class.java)
}