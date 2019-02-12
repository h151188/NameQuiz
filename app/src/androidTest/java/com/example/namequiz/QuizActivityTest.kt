package com.example.namequiz

import android.arch.core.executor.testing.InstantTaskExecutorRule
import android.arch.persistence.room.Room
import android.content.Context
import android.support.test.InstrumentationRegistry
import android.util.Log
import org.junit.After
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.rules.TestRule
import org.junit.runner.RunWith

class QuizActivityTest {

    @Rule
    @JvmField
    val rule: TestRule = InstantTaskExecutorRule()


    private lateinit var quizActivity: QuizActivity

    @Before
    fun setUp() {
        System.out.println("BEFORE")
        val context: Context = InstrumentationRegistry.getTargetContext()
        try {
        updateScore()

        } catch (e: Exception) {
            Log.i("test", e.message)
        }
    }

    @Test
    fun updateScore() {
        var beforeWrong = quizActivity.getScoreWrong()
        var beforeCor = quizActivity.getScoreCorrect()
        quizActivity.testScore(1)
        quizActivity.testScore(2)
        var afterWrong = quizActivity.getScoreWrong()
        var afterCor = quizActivity.getScoreCorrect()


        System.out.println(assertEquals(afterCor, (beforeCor + 1)))
        System.out.println(assertEquals(afterWrong, beforeWrong+1))

    }



}