package com.example.namequiz

import android.app.Activity
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.widget.Toast

class DatabaseActivity : AppCompatActivity() {
    private val newWordActivityRequestCode = 1
    private lateinit var wordViewModel: NamesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_database)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerview)
        val adapter = NameAdapter(this)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Get a new or existing ViewModel from the ViewModelProvider.
        wordViewModel = ViewModelProviders.of(this).get(NamesViewModel::class.java)

        // Add an observer on the LiveData returned by getAlphabetizedWords.
        // The onChanged() method fires when the observed data changes and the activity is
        // in the foreground.
        wordViewModel.allWords.observe(this, Observer { names ->
            // Update the cached copy of the words in the adapter.
            names?.let { adapter.setNames(it) }
        })
    }
}

/*
class DatabaseActivity : AppCompatActivity() {

    private lateinit var adapter: NameAdapter
    private lateinit var namesListView: ListView

    //private lateinit var database: AppDatabase
    //private lateinit var namesDao: NamesDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_database)

        setListView()
    }


    /**
     * Initializes the listview of names
     */
    fun setListView() {
        val gv = applicationContext as GlobalVars

        // Set up our database
        /*try {
            database = Room.inMemoryDatabaseBuilder(this, AppDatabase::class.java)
                .allowMainThreadQueries().build()
        } catch (e: Exception) {
            Log.i("test", e.message)
        }
        namesDao = database.namesDao()*/


        namesListView = findViewById(R.id.list_database) as ListView
        // instantiate and set adapter
        adapter = NameAdapter(this, gv.names)//namesDao.getAll())//gv.names)
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
*/