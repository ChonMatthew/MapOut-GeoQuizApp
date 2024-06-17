package com.example.mapout

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val playButton: Button = findViewById(R.id.button_play)
        val settingsButton: Button = findViewById(R.id.button_settings)
        val quitButton: Button = findViewById(R.id.button_quit)


        playButton.setOnClickListener {
            val intent = Intent(this, QuestionsActivity::class.java)
            startActivity(intent)
            }
        }
    }
   