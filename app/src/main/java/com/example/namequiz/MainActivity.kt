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

   /**     var mPreferences = this.getSharedPreferences("firstTime", Context.MODE_PRIVATE)
        var firstTime: Boolean = mPreferences.getBoolean("firstTime", true)

        if (firstTime) {
            val editor = mPreferences.edit()
            editor.putBoolean("firstTime", false)
            Database().init()
            editor.commit()
            System.out.println("STARTET")
        } */
        var list:MutableList<Names> = ArrayList<Names>()
        var name = Names("Espen", R.drawable.person1)
        list.add(name)
        name = Names("Lars", R.drawable.person1)
        list.add(name)
        name = Names("David", R.drawable.person1)
        list.add(name)
        name = Names("Glenn", R.drawable.person1)
        list.add(name)

        // Get reference to buttons
        val btn_start = findViewById(R.id.button_start) as Button
        val btn_database = findViewById(R.id.button_database) as Button
        val btn_add = findViewById(R.id.button_add) as Button

        // Set on-click listener
        btn_start.setOnClickListener {
            var intent = Intent(applicationContext, QuizActivity::class.java)
            intent.putParcelableArrayListExtra("list", list as java.util.ArrayList<out Parcelable>)
            startActivity(intent)
        }

        btn_database.setOnClickListener {
            var intent = Intent(applicationContext, DatabaseActivity::class.java)
            intent.putParcelableArrayListExtra("list", list as java.util.ArrayList<out Parcelable>)
            startActivity(intent);
        }

        btn_add.setOnClickListener {
            var intent = Intent(this, AddActivity::class.java)
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

        // check if the request code is same as what is passed
        if (requestCode == 3) {
            //var name = data!!.getSerializableExtra("new_name") as Names
            var name = intent.getParcelableExtra("new_name") as Names
            addName(name)

            val toast = Toast.makeText(
                applicationContext,
                R.string.toast_new_name,
                Toast.LENGTH_SHORT
            )
            toast.show()
        }
    }

    fun addName(name: Names) {
        var intent: Intent =intent
        var list:List<Names> = ArrayList<Names>()
        list = intent.getParcelableArrayListExtra("list")
        list.add(name)
        //addList(list)
    }

    fun addList(list: ArrayList<Names>) {
        var intent = Intent(applicationContext, DatabaseActivity::class.java)
        intent.putParcelableArrayListExtra("list", list as java.util.ArrayList<out Parcelable>)
    }
}
