package com.italkutalk.lab4

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*



class SecActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sec)
        //將變數與 XML 元件綁定
        val btnSend = findViewById<Button>(R.id.btn_send)
        val spDrink = findViewById<Spinner>(R.id.sp_drink)
        val radioGroup = findViewById<RadioGroup>(R.id.radioGroup)
        val radioGroup3 = findViewById<RadioGroup>(R.id.radioGroup3)

        //建立選項清單
        val drinkArray = arrayListOf("請選擇","紅茶","奶茶","咖啡")
        //建立adapter變數獲取spinner列表
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, drinkArray)
        //將列表曾加到spDrink
        spDrink.adapter = adapter
        btnSend.setOnClickListener {
            if (spDrink.selectedItem == "請選擇")
                Toast.makeText(this, "請選擇所需飲料",
                    Toast.LENGTH_SHORT).show()
            else {
                //宣告 Bundle
                val b = Bundle()
                //獲取選單內容
                val text = spDrink.selectedItem.toString()
                //取得 Spinner 字串內容，把飲料名稱、甜度與冰塊資訊放入 Bundle
                b.putString("drink", text)
                b.putString("sugar", radioGroup.findViewById<RadioButton>
                    (radioGroup.checkedRadioButtonId).text.toString())
                b.putString("ice", radioGroup3.findViewById<RadioButton>
                    (radioGroup3.checkedRadioButtonId).text.toString())
                //用 Activity.RESULT_OK 標記執行狀態並記錄 Intent
                setResult(Activity.RESULT_OK, Intent().putExtras(b))
                finish()
            }
        }
    }

}

