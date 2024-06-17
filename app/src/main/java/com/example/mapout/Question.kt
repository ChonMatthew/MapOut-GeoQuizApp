package com.example.mapout

import android.content.Context

class Question(
    val id: Int,
    val question: String,
    val optionOne: String,
    val optionTwo: String,
    val optionThree: String,
    val optionFour: String,
    val answer: Int)

object QuestionsList {
    fun getQuestions(context: Context): ArrayList<Question> {
        val questionsList = ArrayList<Question>()

        val question1 = Question(
            id = 1,
            question = context.getString(R.string.question_one),
            optionOne = "Shah Alam",
            optionTwo = "Kuala Lumpur",
            optionThree = "Brunei",
            optionFour = "Bangkok",
            answer = 2
        )
        questionsList.add(question1)

        val question2 = Question(
            id = 2,
            question = R.string.question_two.toString(),
            optionOne = "Atlantic Ocean",
            optionTwo = "Indian Ocean",
            optionThree = "Pacific Ocean",
            optionFour = "Arctic Ocean",
            answer = 3
        )
        questionsList.add(question2)

        val question3 = Question(
            id = 3,
            question = R.string.question_three.toString(),
            optionOne = "Mount Kilimanjaro",
            optionTwo = "K2",
            optionThree = "Mount Everest",
            optionFour = "Mount Fuji",
            answer = 3
        )
        questionsList.add(question3)

        val question4 = Question(
            id = 4,
            question = R.string.question_four.toString(),
            optionOne = "Five",
            optionTwo = "Seven",
            optionThree = "Six",
            optionFour = "Eight",
            answer = 2
        )
        questionsList.add(question4)

        val question5 = Question(
            id = 5,
            question = R.string.question_five.toString(),
            optionOne = "Lake",
            optionTwo = "Both are the same size",
            optionThree = "Depends on the lake",
            optionFour = "Sean",
            answer = 4
        )
        questionsList.add(question5)
        return questionsList

    }
}