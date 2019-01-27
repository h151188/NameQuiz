package com.example.namequiz

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import android.os.Parcelable

class MainActivity : AppCompatActivity() {

    var list:MutableList<Names> = ArrayList<Names>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Get reference to buttons
        val btn_start = findViewById(R.id.button_start) as Button
        val btn_database = findViewById(R.id.button_database) as Button
        val btn_add = findViewById(R.id.button_add) as Button

        // Set on-click listener
        btn_start.setOnClickListener {
            var intent = Intent(applicationContext, QuizActivity::class.java)
            //intent.putParcelableArrayListExtra("list", list as java.util.ArrayList<out Parcelable>)
            startActivity(intent)
        }

        btn_database.setOnClickListener {
            var intent = Intent(applicationContext, DatabaseActivity::class.java)
            //intent.putParcelableArrayListExtra("list", list as java.util.ArrayList<out Parcelable>)
            startActivityForResult(intent, 2);
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

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        //val gv = applicationContext as GlobalVars

        // check if the request code is same as what is passed
        if (requestCode == 2) {

        }

        if (requestCode == 3) {
            //var name = data!!.getSerializableExtra("new_name") as Names
            //var name = data!!.getParcelableExtra("new_name") as Names
            //gv.names.add(name)

            // Adding name to the database
            //list = intent.getParcelableArrayListExtra("list")
            //list.add(name)
            //intent.putParcelableArrayListExtra("list", list as java.util.ArrayList<out Parcelable>)

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
