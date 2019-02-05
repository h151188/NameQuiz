package com.example.namequiz

import android.app.AlertDialog
import android.content.Intent
import android.content.SharedPreferences
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    private var db: AppDatabase? = null

    companion object {
        val PREF_NAME: String = "PrefFile"

    }

    private var appOwner: String = "owner"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        db = AppDatabase.getDatabase(this)
        val names: List<Names>? = db?.namesDao()?.getAll()

        // Get reference to buttons
        val btn_start = findViewById(R.id.button_start) as Button
        val btn_database = findViewById(R.id.button_database) as Button
        val btn_add = findViewById(R.id.button_add) as Button

        checkNameOfOwner()

        // Set on-click listeners
        btn_start.setOnClickListener {
            var intent = Intent(applicationContext, QuizActivity::class.java)
            startActivity(intent)
        }

        btn_database.setOnClickListener {
            var intent = Intent(applicationContext, DatabaseActivity::class.java)
            startActivity(intent);
        }

        btn_add.setOnClickListener {
            var intent = Intent(applicationContext, AddActivity::class.java)
            startActivityForResult(intent, 3)
        }

    }

    private fun checkNameOfOwner() {
        System.out.println("test1")
        val sharedPreferences: SharedPreferences = applicationContext.getSharedPreferences(appOwner, MODE_PRIVATE)
        var ownerSaved: String = sharedPreferences.getString(appOwner, "")
        if(ownerSaved.equals("")){
            ownerSaved = appOwnerInput()
        }

        findViewById<TextView>(R.id.appOwner).setText(ownerSaved)
    }

    // Exit the application
    fun exitApplication() {
        finish();
        System.exit(0);
    }

    /**
     * onActivityResult from "Add new name" activity that displays a toast.
     */
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 3 && resultCode == RESULT_OK) {
            // Toast to show that the name was added to the database
            val toast = Toast.makeText(
                applicationContext,
                R.string.toast_new_name,
                Toast.LENGTH_SHORT
            )
            toast.show()
        }
    }

    private fun appOwnerInput() : String {
        System.out.println("test2")
        var newName: String = ""
        val context = this
        val builder = AlertDialog.Builder(context)
        builder.setTitle("Username")

        val view = layoutInflater.inflate(R.layout.enter_name, null)

        val enterName = view.findViewById(R.id.enterNameEditText) as EditText

        builder.setView(view);

        System.out.println(android.R.string.ok.toString())
        builder.setPositiveButton(android.R.string.ok){ dialog, _ ->
            newName = enterName.text.toString()
            System.out.println("navn: $newName")
            var isValid = true
            if (newName.isBlank()){
                enterName.error = getString(R.string.validation_empty)
                isValid = false
            }
            if (isValid) {

            } else {
                dialog.dismiss()
            }
            builder.setNegativeButton(android.R.string.cancel ) { dialog, _ ->
                dialog.cancel()
            }
            builder.show()
        }
        return newName
    }
}
