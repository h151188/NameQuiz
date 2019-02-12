package com.example.namequiz

import android.arch.core.executor.testing.InstantTaskExecutorRule
import android.content.Context
import android.support.test.InstrumentationRegistry
import android.util.Log
import kotlinx.android.synthetic.main.activity_add.view.*
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.rules.TestRule

class AddActivityTest {


    @Rule
    @JvmField
    val rule: TestRule = InstantTaskExecutorRule()

    private lateinit var nameAdapter: NameAdapter
    private lateinit var appDatabase: AppDatabase
    @Before
    fun setUp() {
        System.out.println("BEFORE")
        val context: Context = InstrumentationRegistry.getTargetContext()
        try {
            addNewName()

        } catch (e: Exception) {
            Log.i("test", e.message)
        }
    }

    @Test
    fun addNewName() {
        var before = nameAdapter.count
        var context = nameAdapter.getContext()
        var test = Names("test", "hei")
        AppDatabase.getDatabase(context).namesDao().insertName(test)
        var after = nameAdapter.count
        System.out.println("Before adding the count is: " + before)
        System.out.println("After adding the count is: " + after)

        System.out.println(assertEquals(before+1, after))


        before = after
        System.out.println("Before deleting the count is: " + before)

        AppDatabase.getDatabase(context).namesDao()?.delete(test)
        after = nameAdapter.count

        System.out.println("After deleting the count is: " + after)

        System.out.println(assertEquals(before-1, after))
    }
}