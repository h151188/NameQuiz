package com.example.namequiz

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import android.os.Parcelable

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Get reference to buttons
        val btn_start = findViewById(R.id.button_start) as Button
        val btn_database = findViewById(R.id.button_database) as Button
        val btn_add = findViewById(R.id.button_add) as Button

        // Set on-click listeners
        btn_start.setOnClickListener {
            var intent = Intent(applicationContext, QuizActivity::class.java)
            //intent.putParcelableArrayListExtra("list", list as java.util.ArrayList<out Parcelable>)
            startActivity(intent)
        }

        btn_database.setOnClickListener {
            var intent = Intent(applicationContext, DatabaseActivity::class.java)
            //intent.putParcelableArrayListExtra("list", list as java.util.ArrayList<out Parcelable>)
            startActivity(intent);
        }

        btn_add.setOnClickListener {
            var intent = Intent(applicationContext, AddActivity::class.java)
            //intent.putParcelableArrayListExtra("list", list as java.util.ArrayList<out Parcelable>)
            startActivityForResult(intent, 3)
        }

    }

    // Exit the application
    fun exitApplication(View: View) {
        finish();
        System.exit(0);
    }

    /**
     * onActivityResult from "Add new name" activity that displays a toast.
     */
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 3) {
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
