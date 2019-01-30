package com.example.namequiz

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

//data class Names(var name: String, var imgId: String)

@Entity(tableName = "namequiz")
data class Names(@ColumnInfo(name="name") var name: String,
                        @ColumnInfo(name="imgId") var imgId: String,
                        @ColumnInfo(name="id") @PrimaryKey(autoGenerate = true) var id: Long = 0)
