package com.example.namequiz

import android.arch.core.executor.testing.InstantTaskExecutorRule
import android.arch.persistence.room.Room
import android.content.Context
import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import android.util.Log
import junit.framework.Assert
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class NamesDaoTest {

    @Rule
    @JvmField
    val rule: TestRule = InstantTaskExecutorRule()

    private lateinit var database: AppDatabase
    private lateinit var namesDao: NamesDao

    @Before
    fun setup() {
        System.out.println("BEFORE")
        val context: Context = InstrumentationRegistry.getTargetContext()
        try {
            database = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java)
                .allowMainThreadQueries().build()
        } catch (e: Exception) {
            Log.i("test", e.message)
        }

        namesDao = database.namesDao()
    }

    @After
    fun tearDown() {
        database.close()
    }

    @Test
    fun testAddingAndRetrievingData() {
        // 1
        val preInsertRetrievedCategories = namesDao.getAll()

        // 2
        val listCategory = Names("Cats","bilde", 1)
        namesDao.insertAll(listCategory)

        //3
        val postInsertRetrievedCategories = namesDao.getAll()
        val sizeDifference = postInsertRetrievedCategories.size - preInsertRetrievedCategories.size
        Assert.assertEquals(1, sizeDifference)
        val retrievedCategory = postInsertRetrievedCategories.last()
        Assert.assertEquals("Cats", retrievedCategory.name)
    }
}