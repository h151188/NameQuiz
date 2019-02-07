package com.example.namequiz

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import android.net.Uri

class QuizActivity : AppCompatActivity() {

    private var quiz_correct: Int = 0
    private var quiz_tries: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)

        // Updates the score and start/continues the quiz
        updateScore()
    }

    /**
     * Starts the quiz and shows a random name
     */
    fun startQuiz() {
        // Get references to ImageView and buttons
        var img = findViewById(R.id.quiz_pic) as ImageView
        var btn_next = findViewById(R.id.button_quiz_next) as Button
        var btn_reset = findViewById(R.id.button_quiz_reset) as Button

        // Getting global arraylist of names
        //var gv = applicationContext as GlobalVars
        //var list = gv.names
        var list: List<Names>? = AppDatabase.getDatabase(this).namesDao()?.getAll()
        var random = list!!.random()

        // Sets the picture of the person to the imageview
        var imgFile = Uri.parse(random.imgId) as Uri
        img.setImageURI(imgFile)

        System.out.println(random.name)

        // Checks if input was correct or not
        btn_next.setOnClickListener {
            checkInput(random)
        }

        // Button for resetting the score
        btn_reset.setOnClickListener {
            resetScore()
        }
    }

    /**
     * Checks if the input name is correct, shows toast of result and updates the score
     */
    fun checkInput(random: Names) {
        var inputField = findViewById(R.id.quiz_input) as EditText
        var input: String = inputField.text.toString()
        inputField.setText("")

        if(input.equals(random.name, true)) {
            quiz_tries++
            quiz_correct++
            val toast = Toast.makeText(
                applicationContext,
                R.string.quiz_correct,
                Toast.LENGTH_SHORT
            )
            toast.show()
        } else {
            quiz_tries++
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

    /**
     * Updates the score view
     */
    fun updateScore() {
        var correctString: String = quiz_correct.toString()
        var triesString: String = quiz_tries.toString()
        var out: String = correctString + "/" + triesString
        var score_field = findViewById(R.id.quiz_show_score) as TextView
        score_field.setText(out)
        startQuiz()
    }

    /**
     * Resets the score view
     */
    fun resetScore() {
        var inputField = findViewById(R.id.quiz_input) as EditText
        inputField.setText("")
        quiz_correct = 0
        quiz_tries = 0
        updateScore()
    }
}