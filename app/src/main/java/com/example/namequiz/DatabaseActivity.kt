package com.example.namequiz

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ListView
import java.util.jar.Attributes
import android.content.Intent
import android.view.View
import android.widget.Button


class DatabaseActivity : AppCompatActivity() {

    private lateinit var adapter: NameAdapter
    private lateinit var namesListView: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_database)

        namesListView = findViewById(R.id.list_database) as ListView

        //var btnEdit = findViewById(R.id.button_database_edit) as Button
        //var btnDelete = findViewById(R.id.button_database_delete) as Button

        /*btnEdit.setOnClickListener {
            editName()
        }*/

        val gv = applicationContext as GlobalVars

        // instantiate and set adapter
        adapter = NameAdapter(this, gv.names)
        namesListView.adapter = adapter
    }
/*
    private fun deleteName(name: Names) {
        val replyIntent = Intent()
        replyIntent.putExtra("delete_name", name)
        setResult(2,replyIntent);
        finish()
    }*/

    private fun editName() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
