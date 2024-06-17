package com.example.mapout

import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.View.OnClickListener
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat

class QuestionsActivity : AppCompatActivity(), OnClickListener {

    private var mCurrentIndex:Int = 0
    private var mQuestionsList: ArrayList<Question>? = null
    private var mSelectedOption: Int = 0

    private lateinit var progressBar: ProgressBar
    private lateinit var progressText: TextView
    private lateinit var questionText: TextView
    private lateinit var option1: TextView
    private lateinit var option2: TextView
    private lateinit var option3: TextView
    private lateinit var option4: TextView
    private lateinit var btn_submit: Button

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_question)

        progressBar = findViewById(R.id.progressBar)
        progressText = findViewById(R.id.tv_progress)
        questionText = findViewById(R.id.tv_question)
        option1 = findViewById(R.id.tv_option1)
        option2 = findViewById(R.id.tv_option2)
        option3 = findViewById(R.id.tv_option3)
        option4 = findViewById(R.id.tv_option4)
        btn_submit = findViewById(R.id.submit_button)

        mQuestionsList = QuestionsList.getQuestions(this)

        setQuestion()

        option1.setOnClickListener(this)
        option2.setOnClickListener(this)
        option3.setOnClickListener(this)
        option4.setOnClickListener(this)
        btn_submit.setOnClickListener(this)


    }

    private fun setQuestion() {
        if (mCurrentIndex < mQuestionsList!!.size) {
            val question = mQuestionsList!![mCurrentIndex]

            questionText.text = question.question
            progressBar.progress = mCurrentIndex
            progressText.text = "$mCurrentIndex" + "/" + progressBar.max

            questionText.text = question!!.question
            option1.text = question.optionOne
            option2.text = question.optionTwo
            option3.text = question.optionThree
            option4.text = question.optionFour

            defaultOption()
        }
    }

    private fun defaultOption() {
        val options = ArrayList<TextView>()
        options.add(0, option1)
        options.add(1, option2)
        options.add(2, option3)
        options.add(3, option4)

        for (option in options) {
            option.setTextColor(Color.parseColor("#FF000000"))
            option.typeface = Typeface.DEFAULT
            option.background = ContextCompat.getDrawable(
                this, R.drawable.question_box
            )
        }
    }

    override fun onClick(v: View?) {
        when(v?.id) {
            R.id.tv_option1 ->{
                selectedOption(option1, 1)
            }
            R.id.tv_option2 ->{
                selectedOption(option2, 2)
            }
            R.id.tv_option3 ->{
                selectedOption(option3, 3)
            }
            R.id.tv_option4 ->{
                selectedOption(option4, 4)
            }
            R.id.submit_button ->{
                when {
                    mCurrentIndex < mQuestionsList!!.size -> {
                        Log.d("QuestionsActivity", "Before increment: mCurrentIndex = $mCurrentIndex")
                        mCurrentIndex++
                        Log.d("QuestionsActivity", "After increment: mCurrentIndex = $mCurrentIndex")
                        setQuestion() // Load the next question
                        btn_submit.text = "Next Question" // Change text if not last question
                    }

                    else -> {
                        btn_submit.text = "Submit" // Change to "Submit" for last question
                        Toast.makeText(this, "You have completed the Quiz!", Toast.LENGTH_SHORT)
                            .show()
                    }
                }


            }
        }
    }

    private fun selectedOption(tv: TextView, selectedOption: Int) {
        defaultOption()
        mSelectedOption = selectedOption

        tv.setTextColor(Color.parseColor("#FF000000"))
        tv.setTypeface(tv.typeface, Typeface.BOLD)
        tv.background = ContextCompat.getDrawable(
            this, R.drawable.question_box_selected
        )
    }
}

