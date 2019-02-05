package com.example.namequiz

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import kotlinx.coroutines.experimental.CoroutineScope
import kotlinx.coroutines.experimental.Dispatchers
import kotlinx.coroutines.experimental.IO
import kotlinx.coroutines.experimental.Job
import kotlinx.coroutines.experimental.android.Main
import kotlinx.coroutines.experimental.launch
import kotlin.coroutines.experimental.CoroutineContext

class NamesViewModel(application: Application) : AndroidViewModel(application) {

    private var parentJob = Job()
    private val coroutineContext: CoroutineContext
        get() = parentJob + Dispatchers.Main
    private val scope = CoroutineScope(coroutineContext)

    private val repository: NamesRepository
    val allWords: LiveData<List<Names>>

    init {
        val namesDao = AppDatabase.getDatabase(application, scope).namesDao()
        repository = NamesRepository(namesDao)
        allWords = repository.allNames
    }

    fun insert(name: Names) = scope.launch(Dispatchers.IO) {
        repository.insert(name)
    }

    override fun onCleared() {
        super.onCleared()
        parentJob.cancel()
    }
}