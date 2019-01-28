package com.example.namequiz

import android.graphics.BitmapFactory
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import java.net.URI
import android.graphics.Bitmap
import android.net.Uri
import java.io.File
import java.nio.file.Files.exists




class QuizActivity : AppCompatActivity() {

    private var quiz_correct: Int = 0
    private var quiz_tries: Int = 0
    //var list:List<Names> = ArrayList<Names>()

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
        var gv = applicationContext as GlobalVars
        var list = gv.names

        var random = list.random()

        // Sets the picture of the person to the imageview
        var imgFile = Uri.parse(random.getImgId()) as Uri
        img.setImageURI(imgFile)

        System.out.println(random.getName())

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

        if(input.equals(random.getName(), true)) {
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
            val toast = Toast.makeText(
                applicationContext,
                R.string.quiz_wrong,
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

    /*
    private fun setPic(img: ImageView, name: Names) {
        // Get the dimensions of the View
        val targetW: Int = img.width
        val targetH: Int = img.height

        val bmOptions = BitmapFactory.Options().apply {
            // Get the dimensions of the bitmap
            inJustDecodeBounds = true
            BitmapFactory.decodeFile(name.getImgId(), this)
            val photoW: Int = outWidth
            val photoH: Int = outHeight

            // Determine how much to scale down the image
            val scaleFactor: Int = Math.min(photoW / targetW, photoH / targetH)

            // Decode the image file into a Bitmap sized to fill the View
            inJustDecodeBounds = false
            inSampleSize = scaleFactor
            inPurgeable = true
        }
        BitmapFactory.decodeFile(name.getImgId(), bmOptions)?.also { bitmap ->
            img.setImageBitmap(bitmap)
        }
    }*/
}
