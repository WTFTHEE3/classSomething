package com.example.sqlite_loginapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class LoginActivity : AppCompatActivity() {
    lateinit var button : Button
    lateinit var username: EditText
    lateinit var password: EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        username = findViewById(R.id.editTextTextEmailAddress3)
        password = findViewById(R.id.editTextTextPassword4)
        button = findViewById(R.id.button)
        var helper = MyDBHelper(applicationContext)
        var db = helper.readableDatabase



        button.setOnClickListener {
            var unameText = username.text.toString()
            var arge = listOf<String>(username.text.toString(), password.text.toString()).toTypedArray()
            var rs = db.rawQuery("SELECT * FROM USERS WHERE UNAME = ? AND PWD = ?",arge)
            if (rs.moveToNext()) {
                val intent = Intent(this, MainActivity::class.java)
                intent.putExtra("Text",unameText)
                password.setText("")
                startActivity(intent)
            } else{
                Toast.makeText(applicationContext, "帳號或密碼錯誤",Toast.LENGTH_LONG).show()
            }
        }
    }
}