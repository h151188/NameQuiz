package com.example.namequiz

import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import org.junit.runner.RunWith
import android.support.test.espresso.Espresso;
import android.support.test.espresso.action.ViewActions
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.filters.LargeTest
import android.util.Log
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@RunWith(AndroidJUnit4::class)
@LargeTest
class QuizActivityTest {

    private lateinit var stringToBetyped: String

    @get:Rule
    var activityRule: ActivityTestRule<QuizActivity>
            = ActivityTestRule(QuizActivity::class.java)

    @Before
    fun setUp() {
        stringToBetyped = "Navnet"
    }


    @Test
    fun guess_wrong(){
        Log.e("@Test","Performing guess wrong test")
        Espresso.onView((withId(R.id.quiz_input)))
            .perform(ViewActions.typeText(stringToBetyped), ViewActions.closeSoftKeyboard())

        Espresso.onView(withId(R.id.button_quiz_next))
            .perform(ViewActions.click());

        Espresso.onView(withId(R.id.quiz_wrong_score))
            .check(matches(withText("1")))
    }
    @Test
    fun guess_correct(){
        Log.e("@Test","Performing guess success test")
        Espresso.onView((withId(R.id.quiz_input)))
            .perform(ViewActions.typeText(activityRule.activity.getName().name), ViewActions.closeSoftKeyboard())

        Espresso.onView(withId(R.id.button_quiz_next))
            .perform(ViewActions.click());

        Espresso.onView(withId(R.id.quiz_correct_score))
            .check(matches(withText("1")))
    }
}