package com.example.namequiz

import android.app.Application
import android.arch.persistence.room.Room

class NamesMasterApplication: Application() {

    companion object {
        var database: AppDatabase? = null
    }

    override fun onCreate() {
        super.onCreate()
        NamesMasterApplication.database = Room.databaseBuilder(this, AppDatabase::class.java,
            "names-db").build()
    }
}