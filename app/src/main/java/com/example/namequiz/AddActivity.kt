package com.example.namequiz

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcelable
import android.provider.MediaStore
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import java.io.Serializable

class AddActivity : AppCompatActivity() {

    val REQUEST_IMAGE_CAPTURE = 1

    private fun dispatchTakePictureIntent() {
        Intent(MediaStore.ACTION_IMAGE_CAPTURE).also { takePictureIntent ->
            takePictureIntent.resolveActivity(packageManager)?.also {
                startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)

        // Get reference to buttons
        var btn_upload = findViewById(R.id.new_upload) as Button
        var btn_take_pic = findViewById(R.id.new_take_pic) as Button
        var btn_add = findViewById(R.id.new_add) as Button

        // Set on-click listener
        //btn_take_pic.setOnClickListener {
        //    intent = Intent.parseIntent(this, dispatchTakePictureIntent)
        //    startActivity(intent)
        //}
        btn_add.setOnClickListener {
            addNewName()
        }
    }

    fun addNewName() {
        var btn_name = findViewById(R.id.new_name) as EditText

        val replyIntent = Intent()
        var name:Names =Names(btn_name.text.toString(), R.drawable.person1)
        replyIntent.putExtra("new_name", name)
        setResult(3,replyIntent);
        finish()
    }

}
