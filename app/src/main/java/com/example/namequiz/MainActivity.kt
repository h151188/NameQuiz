package com.example.namequiz

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.ActionBar
import android.view.View
import android.widget.Button

class MainActivity : AppCompatActivity() {

    data class Names(val name: String = "", val imgId: String);

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Get reference to buttons
        val btn_database = findViewById(R.id.button_database) as Button

        // Set on-click listener
        btn_database.setOnClickListener {
            intent = Intent(this, DatabaseActivity::class.java);
            startActivity(intent);
        }

    }

    // Exit the application
    fun exitApplication(View: View) {
        finish();
        System.exit(0);
    }
}
