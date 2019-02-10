package com.example.namequiz

import android.content.Context
import org.junit.Test

import org.junit.Assert.*

class AddActivityTest {

    private lateinit var nameAdapter: NameAdapter
    private lateinit var appDatabase: AppDatabase


    @Test
    fun addNewName() {
        var before = nameAdapter.count
        var context = nameAdapter.getContext()
        AppDatabase.getDatabase(context).namesDao().insertName("test123")
        var after = nameAdapter.count
        System.out.println("Before adding the count is: " + before)
        System.out.println("After adding the count is: " + after)

        System.out.println(assertEquals(before+1, after))


        before = after
        System.out.println("Before deleting the count is: " + before)

        AppDatabase.getDatabase(context).namesDao()?.delete("test123")
        after = nameAdapter.count

        System.out.println("After deleting the count is: " + after)

        System.out.println(assertEquals(before-1, after))
    }
}