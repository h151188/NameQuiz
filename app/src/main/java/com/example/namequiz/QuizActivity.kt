package com.example.namequiz

import android.graphics.BitmapFactory
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.*


class QuizActivity : AppCompatActivity() {

    private var quiz_correct: Int = 0
    private var quiz_tries: Int = 0
    //var list:List<Names> = ArrayList<Names>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)
       // var intent: Intent =intent
       // list = intent.getParcelableArrayListExtra("list")

        updateScore()
    }

    fun startQuiz() {
        var img = findViewById(R.id.quiz_pic) as ImageView
        var btn_next = findViewById(R.id.button_quiz_next) as Button
        var btn_reset = findViewById(R.id.button_quiz_reset) as Button

        val gv = applicationContext as GlobalVars
        var list = gv.names

        var random = list.random()
        //img.setImageResource(random.getImgId())
        //setPic(img, random)

        System.out.println(random.getName())

        btn_next.setOnClickListener {
            checkInput(random)
        }

        btn_reset.setOnClickListener {
            resetScore()
        }
    }

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

    fun updateScore() {
        var correctString: String = quiz_correct.toString()
        var triesString: String = quiz_tries.toString()
        var out: String = correctString + "/" + triesString
        var score_field = findViewById(R.id.quiz_show_score) as TextView
        score_field.setText(out)
        startQuiz()
    }

    fun resetScore() {
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
