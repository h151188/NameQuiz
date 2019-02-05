package com.example.namequiz

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.Room
import android.content.Context

@Database(entities = arrayOf(Names::class), version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun namesDao(): NamesDao

    companion object {

        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            if (INSTANCE == null) {
                synchronized(AppDatabase::class) {
                    INSTANCE = Room.databaseBuilder(
                        context.getApplicationContext(),
                        AppDatabase::class.java, "names-db"
                    ).allowMainThreadQueries().build()
                }
            }
            return INSTANCE!!
        }
    }
}

