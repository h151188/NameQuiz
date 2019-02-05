package com.example.namequiz

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query

@Dao
interface NamesDao {

    @Query("SELECT * FROM namequiz ORDER BY name ASC")
    fun getAll(): List<Names>

    @Insert
    fun insertName(vararg names: Names)

    @Insert
    fun insertAllNames(names: List<Names>)
}