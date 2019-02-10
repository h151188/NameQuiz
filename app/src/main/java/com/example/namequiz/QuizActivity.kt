package com.example.namequiz

import android.app.AlertDialog
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import android.net.Uri
import android.view.Menu
import android.view.MenuItem

class QuizActivity : AppCompatActivity() {

    private var quiz_correct: Int = 0
    private var quiz_wrong: Int = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)

        var emoji_correct = findViewById(R.id.quiz_correct_emoji) as TextView
        var emoji_wrong = findViewById(R.id.quiz_wrong_emoji) as TextView

        emoji_correct.setText(getEmojiByUnicode(0x2714))
        emoji_wrong.setText(getEmojiByUnicode(0x274C))

        // Updates the score and start/continues the quiz
        updateScore()
    }

    /**
     * Returns a String of given unicode Int for emoji-use
     */
    fun getEmojiByUnicode(unicode: Int): String {
        return String(Character.toChars(unicode))
    }

    /**
     * Starts the quiz and shows a random name
     */
    fun startQuiz() {
        // Get references to ImageView and buttons
        var img = findViewById(R.id.quiz_pic) as ImageView
        var btn_next = findViewById(R.id.button_quiz_next) as Button

        var list: List<Names> = AppDatabase.getDatabase(this).namesDao().getAll()
        var random: Names?

        if(!list.isNullOrEmpty()) {
            random = list.random()

            // Sets the picture of the person to the imageview
            var imgFile = Uri.parse(random.imgId) as Uri
            img.setImageURI(imgFile)

            // Checks if input was correct or not
            btn_next.setOnClickListener {
                checkInput(random)
            }

        } else {
            img.setImageResource(R.drawable.person2)
            emptyDatabase()
        }
    }

    /**
     * Shows a toast that the database is empty
     */
    private fun emptyDatabase() {
        val toast = Toast.makeText(
            applicationContext, R.string.quiz_db_empty,
            Toast.LENGTH_SHORT
        )
        toast.show()
    }

    /**
     * Checks if the input name is correct, shows toast of result and updates the score
     */
    fun checkInput(random: Names) {
        var inputField = findViewById(R.id.quiz_input) as EditText
        var input: String = inputField.text.toString()
        inputField.setText("")


        if(input.equals(random.name, true)) {
            quiz_correct++
            val toast = Toast.makeText(
                applicationContext,
                R.string.quiz_correct,
                Toast.LENGTH_SHORT
            )
            toast.show()
        } else {
            quiz_wrong++
            var wrong: String = resources.getString(R.string.quiz_wrong)
            val toast = Toast.makeText(
                applicationContext,
                wrong + " " + random.name,
                Toast.LENGTH_SHORT
            )
            toast.show()
        }
        updateScore()
    }
    fun testScore(testVal: Int) {
        if(testVal == 1) {
            quiz_correct++
        } else if (testVal == 2) {
            quiz_wrong++
        }
    }
    /**
     * Updates the score view
     */
    fun updateScore() {
        var correctString: String = quiz_correct.toString()
        var wrongString: String = quiz_wrong.toString()

        var score_correct = findViewById(R.id.quiz_correct_score) as TextView
        var score_wrong = findViewById(R.id.quiz_wrong_score) as TextView

        score_correct.setText(correctString)
        score_wrong.setText(wrongString)

        startQuiz()
    }

    fun getScoreCorrect(): Int {
        return quiz_correct
    }

    fun getScoreWrong(): Int {
        return quiz_wrong
    }
    /**
     * Creates an options menu to select activity
     */
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_quiz, menu)
        return super.onCreateOptionsMenu(menu)
        //return true
    }

    /**
     * Starts the activity selected from the options menu
     */
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.getItemId()
        if (id == R.id.menu_quiz_reset) {
            resetConfirmation()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    /**
     * Resets the score view
     */
    fun resetConfirmation() {
        val builder = AlertDialog.Builder(this@QuizActivity)

        // Set the alert dialog title
        builder.setTitle(R.string.quiz_reset_score)

        // Display a message on alert dialog
        builder.setMessage(R.string.quiz_reset_score_confirmation)

        // Set a positive button and its click listener on alert dialog
        builder.setPositiveButton(R.string.ok){dialog, which ->
            // Do something when user press the positive button
            var inputField = findViewById(R.id.quiz_input) as EditText
            inputField.setText("")
            quiz_correct = 0
            quiz_wrong = 0
            updateScore()
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
