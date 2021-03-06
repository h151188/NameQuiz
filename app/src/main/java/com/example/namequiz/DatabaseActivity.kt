package com.example.namequiz

import android.app.AlertDialog
import android.app.Dialog
import android.app.DialogFragment
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.ListView
import android.widget.Toast
import android.widget.BaseAdapter



class DatabaseActivity : AppCompatActivity() {

    private lateinit var adapter: NameAdapter
    private lateinit var namesListView: ListView
    private lateinit var names: MutableList<Names>

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
        names =  AppDatabase.getDatabase(this).namesDao()?.getAll()

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
        } else if (id == R.id.menu_db_prepopulate) {
            val gv = applicationContext as GlobalVars
            var newList = gv.initArray()
            AppDatabase.getDatabase(this).namesDao().insertAllNames(newList)
            names.addAll(newList)
            var listView = findViewById(R.id.list_database) as ListView
            (listView.getAdapter() as BaseAdapter).notifyDataSetChanged()
            return true
        } else if (id == R.id.menu_db_delete_all) {
            deleteAllConfirmation()
            return true
        } else if (id == R.id.menu_change_preferences) {
            val intent = Intent(applicationContext, PreferencesActivity::class.java)
            startActivity(intent)
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

    fun deleteAllConfirmation() {
        val builder = AlertDialog.Builder(this@DatabaseActivity)

        // Set the alert dialog title
        builder.setTitle(R.string.db_delete_all_confirmation_title)

        // Display a message on alert dialog
        builder.setMessage(R.string.db_delete_all_confirmation)

        // Set a positive button and its click listener on alert dialog
        builder.setPositiveButton(R.string.ok){dialog, which ->
            // Do something when user press the positive button
            AppDatabase.getDatabase(this).namesDao().deleteAll()
            names.clear()
            var listView = findViewById(R.id.list_database) as ListView
            (listView.getAdapter() as BaseAdapter).notifyDataSetChanged()
        }


        // Display a negative button on alert dialog
        builder.setNegativeButton(R.string.cancel){dialog,which ->

        }

        // Finally, make the alert dialog using builder
        val dialog: AlertDialog = builder.create()

        // Display the alert dialog on app interface
        dialog.show()
    }

}