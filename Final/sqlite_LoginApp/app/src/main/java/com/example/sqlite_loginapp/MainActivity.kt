package com.example.sqlite_loginapp

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.time.ZoneId
import java.time.ZonedDateTime


class MainActivity : AppCompatActivity() {
    //延後建立物件
    lateinit var UnameText: TextView
    lateinit var viewTimeText: TextView
    lateinit var backButton : Button
    lateinit var timeButton : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //讀取傳送資料，並使用key定位
        val textE = intent.getStringExtra("Text")
        //綁定物件實體
        UnameText = findViewById(R.id.ViewText)
        viewTimeText = findViewById(R.id.viewTimeText)
        backButton = findViewById(R.id.button2)
        timeButton = findViewById(R.id.button3)
        //將使用者帳號顯示出來
        UnameText.text = "Hello! "+textE

        //建立按鈕事件
        backButton.setOnClickListener {
            //登出
            finish()
        }
        timeButton.setOnClickListener {
            //取得現在時間
            getTime()
            //取消 viewTimeText 內容隱藏，並顯示出現在時間
            viewTimeText.visibility = View.VISIBLE
        }
    }
    //
    private fun getTime(){
        //此處ZoneId.of 、 ZonedDateTime.now 需要調整API級別到26，但不影響使用

        //設定時區
        val londonZone = ZoneId.of("GMT+8")
        //取得現在時間(預設GMT+0)，並帶入londonZone 設定時區
        val londonCurrentDateTime = ZonedDateTime.now(londonZone)
        viewTimeText.text = londonCurrentDateTime.toString()
    }

}


