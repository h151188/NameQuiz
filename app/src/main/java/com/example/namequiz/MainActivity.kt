package com.example.namequiz

import android.app.AlertDialog
import android.content.Intent
import android.content.SharedPreferences
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import android.widget.EditText
import android.widget.TextView
import org.w3c.dom.Text

class MainActivity : AppCompatActivity() {

    private var appOwner: String = "owner"
    private var sharedPreferences: SharedPreferences? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Get reference to buttons
        val btn_start = findViewById(R.id.button_start) as Button
        val btn_database = findViewById(R.id.button_database) as Button
        val btn_add = findViewById(R.id.button_add) as Button
        val btn_ok = findViewById(R.id.button_ok) as Button



        this.checkNameOfOwner()

        // Set on-click listeners
        btn_ok.setOnClickListener {
            val name =  findViewById<TextView>(R.id.appOwner).getText().toString()
            val editor = sharedPreferences!!.edit()
            editor.putString(appOwner, name)
            editor.apply()
            btn_ok.visibility = View.INVISIBLE

        }

        btn_start.setOnClickListener {
            val intent = Intent(applicationContext, QuizActivity::class.java)
            startActivity(intent)
        }

        btn_database.setOnClickListener {
            val intent = Intent(applicationContext, DatabaseActivity::class.java)
            startActivity(intent)
        }

        btn_add.setOnClickListener {
            val intent = Intent(applicationContext, AddActivity::class.java)
            startActivityForResult(intent, 3)
        }

    }

    private fun checkNameOfOwner() {

        findViewById<TextView>(R.id.appOwnerSaved).visibility = View.INVISIBLE

        sharedPreferences = applicationContext.getSharedPreferences(appOwner, MODE_PRIVATE)

        val name = sharedPreferences!!.getString(appOwner, "")
        if (name.equals("")){
            findViewById<TextView>(R.id.appOwner).visibility = View.VISIBLE
            findViewById<TextView>(R.id.appOwner).setHint("Please enter name")
        } else {
            findViewById<TextView>(R.id.button_ok).visibility = View.INVISIBLE
            findViewById<TextView>(R.id.appOwner).visibility = View.INVISIBLE
            findViewById<TextView>(R.id.appOwnerSaved).visibility = View.VISIBLE
            findViewById<TextView>(R.id.appOwnerSaved).setText(name)
        }

    }

    // Exit the application
    fun exitApplication(view: View) {
        finish()
        System.exit(0)
    }

}
