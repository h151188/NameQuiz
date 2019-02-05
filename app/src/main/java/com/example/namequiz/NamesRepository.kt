/*package com.example.namequiz

import android.arch.lifecycle.LiveData
import android.support.annotation.WorkerThread

class NamesRepository(private val namesDao: NamesDao) {

    val allNames: LiveData<List<Names>> = namesDao.getAll()

    @WorkerThread
    suspend fun insert(name: Names) {
        namesDao.insertName(name)
    }
}*/