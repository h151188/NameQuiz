package com.example.namequiz

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView

class DatabaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_database)

        var listView: ListView? = findViewById<ListView>(R.id.list_database);
        var adapter = NameAdapter(this, generateData())
        listView?.adapter = adapter;

        adapter.notifyDataSetChanged()
    }

    fun generateData(): ArrayList<MainActivity.Names> {
        var result = ArrayList<MainActivity.Names>()

        for (i in 0..9) {
            var name: MainActivity.Names = MainActivity.Names("Bett", "person1.png")
            result.add(name)
        }

        return result
    }
}
