package com.example.mapout

import android.content.Intent
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

// Giving the main class a OnClickListener
class QuestionsActivity : AppCompatActivity(), OnClickListener {

    // Initializing the Questions and the POSSIBLE Selected Options
    private var mCurrentIndex:Int = 1
    private var mQuestionsList: ArrayList<Question>? = null
    private var mSelectedOption: Int = 0

    // Using a HashMap to store the Selected Options
    private var mPreviouslySelectedOptions: HashMap<Int, Int> = hashMapOf()

    // lateinit is used to avoid conflict when initializing the layout in the onCreate()
    private lateinit var progressBar: ProgressBar
    private lateinit var progressText: TextView
    private lateinit var questionText: TextView
    private lateinit var option1: TextView
    private lateinit var option2: TextView
    private lateinit var option3: TextView
    private lateinit var option4: TextView
    private lateinit var btn_submit: Button
    private lateinit var btn_back: Button
    private lateinit var options: ArrayList<TextView>

    // Layout of the Questions Screen
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Content Layout is from the activity_question.xml file
        setContentView(R.layout.activity_question)

        // Initializing all the lateinit variables
        progressBar = findViewById(R.id.progressBar)
        progressText = findViewById(R.id.tv_progress)
        questionText = findViewById(R.id.tv_question)
        option1 = findViewById(R.id.tv_option1)
        option2 = findViewById(R.id.tv_option2)
        option3 = findViewById(R.id.tv_option3)
        option4 = findViewById(R.id.tv_option4)
        btn_submit = findViewById(R.id.submit_button)
        btn_submit = findViewById(R.id.submit_button)
        btn_back = findViewById(R.id.back_button)

        // Using the getQuestions() function from the Questions.kt file to fill the list
        mQuestionsList = QuestionsList.getQuestions(this)

        // Setting up the texts for the questions and options
        setQuestion()

        // Setting up onClickListeners for all the options and buttons
        option1.setOnClickListener(this)
        option2.setOnClickListener(this)
        option3.setOnClickListener(this)
        option4.setOnClickListener(this)
        btn_submit.setOnClickListener(this)
        btn_back.setOnClickListener(this)
    }

    // Function to get all the text for the questions and options
    private fun setQuestion() {
        // Setting the SelectedOption to 0 to ensure the options are printed unselected when first run
        mSelectedOption = 0

        // Controller to ensure the CurrentIndex (Question) does not exceed the limit
        if (mCurrentIndex <= mQuestionsList!!.size) {
            // Variable to get the parameters of the question
            val question = mQuestionsList!![mCurrentIndex - 1]

            questionText.text = question.question // Question variable

            // Progress bar printed with the actual progress
            progressBar.progress = mCurrentIndex
            progressText.text = "$mCurrentIndex" + "/" + progressBar.max

            // Printing the question parameters
            questionText.text = question!!.question
            option1.text = question.optionOne
            option2.text = question.optionTwo
            option3.text = question.optionThree
            option4.text = question.optionFour

            // Initializing the variable to receive the options stored in the hashmap
            val previouslySelectedOption = mPreviouslySelectedOptions[mCurrentIndex - 1]

            // If the HashMap is not empty
            if (previouslySelectedOption != null) {
                val option = options[previouslySelectedOption - 1] // Get option from the ArrayList of TextViews based on index
                selectedOption(option, previouslySelectedOption) // Simulate selecting the option using the Int from the HashMap
            } else {
                defaultOption() // Printing the default options style
            }

            // Changes the Button to reflect the progress of the quiz
            if (mCurrentIndex == mQuestionsList!!.size) {
                btn_submit.text = "Submit" // Change to "Submit" for last question
            } else {
                btn_submit.text = "Next Question" // Change text if not last question
            }
        }
    }

    // The default styling of the options
    private fun defaultOption() {
        options = ArrayList()
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

    // Making the onClick functions for each onClickListeners
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
                // If the selected option is more than 0, it goes to the next question
                if (mSelectedOption > 0) {
                    mCurrentIndex++
                    setQuestion()
                } else {
                    Toast.makeText(this, "Please select an answer!", Toast.LENGTH_SHORT).show()
                }
                // If there are no more questions in the list
                if (mCurrentIndex > mQuestionsList!!.size) {
                    val score = calculateScore() // Implement calculateScore() function

                    // Goes to the next Screen
                    val intent = Intent(this, ResultScreen::class.java)
                    intent.putExtra("total_questions", mQuestionsList!!.size)
                    intent.putExtra("correct_answers", score)
                    startActivity(intent)
                    finish() // Finish QuestionsActivity
                }
            }
            // Button to go to the previous question
            R.id.back_button -> {
                // Controller to avoid going back if there are no more questions
                if (mCurrentIndex > 1) {
                    mCurrentIndex--
                    setQuestion()
                } else {
                    Toast.makeText(this, "You are on the first question!", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    // Function to change the TextView is an Option is selected
    private fun selectedOption(tv: TextView, selectedOption: Int) {
        // This sets all the Options back to normal
        defaultOption()

        // The mSelectedOption is used as the parameter of this function
        mSelectedOption = selectedOption
        mPreviouslySelectedOptions[mCurrentIndex - 1] = mSelectedOption // Store selection for current question

        // Styling
        tv.setTextColor(Color.parseColor("#FF000000"))
        tv.setTypeface(tv.typeface, Typeface.BOLD)
        tv.background = ContextCompat.getDrawable(this, R.drawable.question_box_selected)
    }


    private fun calculateScore(): Int {
        var score = 0
        for (i in 0 until mQuestionsList!!.size) {
            val question = mQuestionsList!![i]
            val selectedOption = mPreviouslySelectedOptions[i] // Access using question index + 1
            if (selectedOption != null && selectedOption == question.answer) {
                score++
            }
        }
        return score
    }
}

