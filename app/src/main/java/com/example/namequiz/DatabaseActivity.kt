package com.example.namequiz

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ListView
import java.util.jar.Attributes
import android.content.Intent



class DatabaseActivity : AppCompatActivity() {

    private lateinit var adapter: NameAdapter
    private lateinit var namesListView: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_database)

        namesListView = findViewById(R.id.list_database) as ListView

        // Get list of members
        //val i = intent
        //val navBarTitle: String = R.string.database.toString()
        //supportActionBar?.title = navBarTitle
        //var list = i.getSerializableExtra("LIST") as ArrayList<Names>

        var intent: Intent =intent
        var list:List<Names> = ArrayList<Names>()
        list = intent.getParcelableArrayListExtra("list")

        // instantiate and set adapter
        adapter = NameAdapter(this, list)
        namesListView.adapter = adapter
    }
}
