package com.example.namequiz

import android.content.Intent
import android.content.SharedPreferences
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class PreferencesActivity : AppCompatActivity() {

    private var appOwner: String = "owner"
    private var sharedPreferences: SharedPreferences? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_preferences)

        val btn_ok = findViewById<Button>(R.id.button_ok)
        sharedPreferences = applicationContext.getSharedPreferences(appOwner, MODE_PRIVATE)
        val nameSaved = sharedPreferences!!.getString(appOwner, "")
        if (!nameSaved.equals("")){
            findViewById<EditText>(R.id.appOwner).setText(nameSaved)
        }

        btn_ok.setOnClickListener {
            val name =  findViewById<TextView>(R.id.appOwner).text.toString()
            val editor = sharedPreferences!!.edit()
            editor.putString(appOwner, name)
            editor.apply()
            val intent = Intent(applicationContext, MainActivity::class.java)
            startActivity(intent)
        }

    }
}
