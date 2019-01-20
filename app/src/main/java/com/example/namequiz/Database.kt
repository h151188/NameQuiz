package com.example.namequiz

import java.io.Serializable

class Database : Serializable {
    private var list: ArrayList<Names> = arrayListOf()

    fun init() {
        var name = Names("Espen", R.drawable.person1)
        list.add(name)
        name = Names("Lars", R.drawable.person1)
        list.add(name)
        name = Names("David", R.drawable.person1)
        list.add(name)
        name = Names("Randi", R.drawable.person1)
        list.add(name)
    }

    fun addName(name: Names) {
        list.add(name)
    }

    fun getList() : ArrayList<Names> {
        return list;
    }
}