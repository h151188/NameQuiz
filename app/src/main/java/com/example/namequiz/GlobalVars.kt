package com.example.namequiz

import android.app.Application


class GlobalVars : Application() {
    var names = ArrayList<Names>()

    override fun onCreate() {
        super.onCreate()
        names.addAll(initArray())
    }

    fun initArray(): ArrayList<Names> {
        var list = ArrayList<Names>()
        //var name = Names("Espen", "R.drawable.person1")
        //list.add(name)
        var name = Names("Lars", "/storage/emulated/0/Android/data/com.example.namequiz/files/Pictures/lars.jpg")
        list.add(name)
        /*name = Names("David", R.drawable.person1)
        list.add(name)
        name = Names("Glenn", R.drawable.person1)
        list.add(name)*/
        return list
    }
}