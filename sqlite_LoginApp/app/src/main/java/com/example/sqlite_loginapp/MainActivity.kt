package com.example.sqlite_loginapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView


class MainActivity : AppCompatActivity() {
    lateinit var UnameText: TextView
    lateinit var backButton : Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val textE = intent.getStringExtra("Text")
        backButton = findViewById(R.id.button2)
        UnameText = findViewById(R.id.ViewText)
        UnameText.text = "Hello! "+textE

        backButton.setOnClickListener {
            finish()
        }

    }
}


