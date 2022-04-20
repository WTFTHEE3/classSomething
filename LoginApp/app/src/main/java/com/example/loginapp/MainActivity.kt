package com.example.loginapp

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    lateinit var username: EditText
    lateinit var password: EditText
    lateinit var btnLogin: Button
    val correct_username = "test"
    val correct_password = "password"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        username = findViewById(R.id.username)
        password = findViewById(R.id.password)
        btnLogin = findViewById(R.id.button)

        btnLogin.setOnClickListener {
            if (TextUtils.isEmpty(username.text.toString()) || TextUtils.isEmpty(password.text.toString())) {
                Toast.makeText(this, "Invalid Inputs", Toast.LENGTH_SHORT).show()
            } else if (username.text.toString().equals(correct_username)) {
                if (password.text.toString().equals(correct_password)) {
                    Toast.makeText(this, "Success Login", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Invalid UserName/PassWord", Toast.LENGTH_SHORT).show()
            }
        }
    }
}