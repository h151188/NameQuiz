package com.example.namequiz

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query

@Dao
interface NamesDao {

    @Query("SELECT * FROM namequiz")
    fun getAll(): LiveData<List<Names>>

    @Insert
    fun insertName(vararg names: Names)

    @Insert
    fun insertAllNames(names: List<Names>)
}