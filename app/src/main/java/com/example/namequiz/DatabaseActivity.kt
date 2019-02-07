package com.example.namequiz

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.ListView
import android.widget.Toast

class DatabaseActivity : AppCompatActivity() {

    private lateinit var adapter: NameAdapter
    private lateinit var namesListView: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_database)

        setListView()
    }

    /**
     * Initializes the listview of names
     */
    fun setListView() {
        //val gv = applicationContext as GlobalVars

        // Set up our database
        var names: MutableList<Names>? =  AppDatabase.getDatabase(this).namesDao()?.getAll()

        namesListView = findViewById(R.id.list_database) as ListView

        // instantiate and set adapter
        adapter = NameAdapter(this, names)
        namesListView.adapter = adapter
    }

    /**
     * Creates an options menu to select activity
     */
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_database, menu)
        return super.onCreateOptionsMenu(menu)
        //return true
    }

    /**
     * Starts the activity selected from the options menu
     */
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.getItemId()
        if (id == R.id.menu_db_add_new_name) {
            var intent = Intent(applicationContext, AddActivity::class.java)
            startActivityForResult(intent, 2)
        }
        return super.onOptionsItemSelected(item)
    }

    /**
     * onActivityResult from "Add new name" activity that displays a toast.
     */
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 2 && resultCode == RESULT_OK) {
            // Refreshes the listview with the new name
            //namesListView.deferNotifyDataSetChanged()
            setListView()
            // Toast to show that the name was added to the database
            val toast = Toast.makeText(
                applicationContext,
                R.string.toast_new_name,
                Toast.LENGTH_SHORT
            )
            toast.show()
        }
    }
}