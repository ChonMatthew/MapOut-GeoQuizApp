package com.example.mapout

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ResultScreen : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result_screen) // Define your layout

        val totalQuestions = intent.getIntExtra("total_questions", 0)
        val correctAnswers = intent.getIntExtra("correct_answers", 0)
        val cheatsUsed = intent.getIntExtra("cheat_used", 0)

        // Find views from your layout (e.g., TextView for displaying score)
        val scoreText = findViewById<TextView>(R.id.tv_score)
        scoreText.text = "You answered $correctAnswers out of $totalQuestions questions correctly."

        val cheatText = findViewById<TextView>(R.id.tv_cheat)
        cheatText.text = "You used $cheatsUsed out of 3 available cheats."

        // Handle button clicks (e.g., finish the activity)
        val finishButton = findViewById<Button>(R.id.btn_finish)
        finishButton.setOnClickListener { finish() }
    }
}
