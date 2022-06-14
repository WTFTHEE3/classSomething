package com.example.sqlite_loginapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class LoginActivity : AppCompatActivity() {
    //延後建立物件
    lateinit var button : Button
    lateinit var username: EditText
    lateinit var password: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //綁定物件實體
        setContentView(R.layout.activity_login)
        username = findViewById(R.id.editTextTextEmailAddress3)
        password = findViewById(R.id.editTextTextPassword4)
        button = findViewById(R.id.button)
        //建立MyDBHelper實體
        var helper = MyDBHelper(applicationContext)
        var db = helper.readableDatabase


        //按鈕觸發事件
        button.setOnClickListener {
            //取得登入者的帳號字串
            var unameText = username.text.toString()
            //將輸入的帳號密碼與資料庫聯繫，確認是否有這筆資料
            var arge = listOf<String>(username.text.toString(), password.text.toString()).toTypedArray()
            var rs = db.rawQuery("SELECT * FROM USERS WHERE UNAME = ? AND PWD = ?",arge)

            if (rs.moveToNext()) {
                //建立 intent，設定啟動的Activity ，並建立key為 Text 來儲存帳號字串
                val intent = Intent(this, MainActivity::class.java)
                intent.putExtra("Text",unameText)
                //重製密碼輸入
                password.setText("")
                //發送Toast，登入成功
                Toast.makeText(applicationContext, "登入成功!",Toast.LENGTH_LONG).show()
                //起動 MainActivity
                startActivity(intent)
            } else{
                //發送登入失敗訊息
                Toast.makeText(applicationContext, "帳號或密碼錯誤",Toast.LENGTH_LONG).show()
            }
        }
    }
}