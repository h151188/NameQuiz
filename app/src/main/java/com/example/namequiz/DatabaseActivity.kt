package com.example.namequiz

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView
import android.content.Intent
import android.R
import android.view.Menu
import android.view.MenuItem

class DatabaseActivity : AppCompatActivity() {

    private lateinit var adapter: NameAdapter
    private lateinit var namesListView: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_database)

        namesListView = findViewById(R.id.list_database) as ListView

        //var btnDelete = findViewById(R.id.button_database_delete) as Button

        val gv = applicationContext as GlobalVars

        // instantiate and set adapter
        adapter = NameAdapter(this, gv.names)
        namesListView.adapter = adapter
        /*
        btnDelete.setOnClickListener {

            gv.names.remove()
            namesListView.invalidateViews();
        }*/
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_database, menu)
        return super.onCreateOptionsMenu(menu)
        //return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.getItemId()
        if (id == R.id.menu_db_add_new_name) {
            var intent = Intent(applicationContext, AddActivity::class.java)
            startActivity(intent)
        }
        return super.onOptionsItemSelected(item)
    }
/*
    private fun deleteName(name: Names) {
        val replyIntent = Intent()
        replyIntent.putExtra("delete_name", name)
        setResult(2,replyIntent);
        finish()
    }*/
}
