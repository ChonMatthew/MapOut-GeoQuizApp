package com.example.mapout

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.mapout.ui.theme.MapOutTheme

class ResultScreen : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result_screen) // Define your layout

        val totalQuestions = intent.getIntExtra("total_questions", 0)
        val correctAnswers = intent.getIntExtra("correct_answers", 0)

        // Find views from your layout (e.g., TextView for displaying score)
        val scoreText = findViewById<TextView>(R.id.tv_score)
        scoreText.text = "You answered $correctAnswers out of $totalQuestions questions correctly."

        // Handle button clicks (e.g., finish the activity)
        val finishButton = findViewById<Button>(R.id.btn_finish)
        finishButton.setOnClickListener { finish() }
    }
}
