package com.example.namequiz

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context

@Database(entities = arrayOf(Names::class), version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun namesDao(): NamesDao
/*
    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "Names_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }*/
}