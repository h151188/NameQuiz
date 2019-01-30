package com.example.namequiz

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query

@Dao
interface NamesDao {

    @Query("SELECT * FROM namequiz")
    fun getAll(): List<Names>

    @Insert
    fun insertAll(vararg names: Names)
}