package com.example.namequiz

import android.support.test.espresso.Espresso.closeSoftKeyboard
import android.support.test.filters.LargeTest
import org.junit.Before
import org.junit.Test
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions
import android.support.test.espresso.action.ViewActions.*
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import org.junit.Rule
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
@LargeTest
public class AddActivityTest {

    private lateinit var stringToBeTyped: String

    @get:Rule
    var activityRule: ActivityTestRule<AddActivity> = ActivityTestRule(AddActivity::class.java)

    @Before
    fun initValidString() {
        // Specify a valid string.
        stringToBeTyped = "Typing test name"
    }


    @Test
    fun changeText_sameActivity() {
        // Type text and then press the button.

        onView(withId(R.id.new_name)).perform(typeText(stringToBeTyped), ViewActions.closeSoftKeyboard())

        onView(withId(R.id.new_add)).perform(click())

    }

}