package com.example.namequiz

import android.arch.persistence.room.*

@Dao
interface NamesDao {

    @Query("SELECT * FROM namequiz ORDER BY name ASC")
    fun getAll(): MutableList<Names>

    @Insert
    fun insertName(vararg names: Names)

    @Insert
    fun insertAllNames(names: List<Names>)

    @Delete
    fun delete(name: Names)

    @Query("SELECT * FROM namequiz WHERE id LIKE :Id")
    fun findByName(Id: Int) : Names

    @Query("DELETE FROM namequiz")
    fun deleteAll()

    //@Insert(onConflict = OnConflictStrategy.REPLACE)
    //fun insertName(name: Names)
}