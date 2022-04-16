package com.italkutalk.lab9_2

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.view.View
import android.widget.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


import kotlin.math.pow

class MainActivity : AppCompatActivity() {
    private var progress1 = 0
    //建立變數以利後續綁定元件
    private lateinit var btn_calculate: Button
    private lateinit var ed_height: EditText
    private lateinit var ed_weight: EditText
    private lateinit var ed_age: EditText
    private lateinit var tv_weight: TextView
    private lateinit var tv_fat: TextView
    private lateinit var tv_bmi: TextView
    private lateinit var tv_progress: TextView
    private lateinit var progressBar2: ProgressBar
    private lateinit var ll_progress: LinearLayout
    private lateinit var btn_boy: RadioButton
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //將變數與 XML 元件綁定
        btn_calculate = findViewById(R.id.btn_calculate)
        ed_height = findViewById(R.id.ed_height)
        ed_weight = findViewById(R.id.ed_weight)
        ed_age = findViewById(R.id.ed_age)
        tv_weight = findViewById(R.id.tv_weight)
        tv_fat = findViewById(R.id.tv_fat)
        tv_bmi = findViewById(R.id.tv_bmi)
        tv_progress = findViewById(R.id.tv_progress)
        progressBar2 = findViewById(R.id.progressBar2)
        ll_progress = findViewById(R.id.ll_progress)
        btn_boy = findViewById(R.id.btn_boy)

        //對計算按鈕設定監聽器
        btn_calculate.setOnClickListener {
            //初始化進度條
            progressBar2.progress =0
            tv_progress.text = "0%"
            progress1 = 0
            //初始化 text 顯示
            tv_weight.text = "標準體重\n 無"
            tv_fat.text = "體脂肪\n 無"
            tv_bmi.text = "BMI\n 無"
            if (ed_height.length() >0&&ed_weight.length()>0&&ed_age.length()>0){
                runCoroutines()
            }else {
                when {
                    ed_height.length() < 1 -> showToast("請輸入身高")
                    ed_weight.length() < 1 -> showToast("請輸入體重")
                    else -> showToast("請輸入年齡")
                }
            }
        }
    }
    //建立 showToast 方法顯示 Toast 訊息
    private fun showToast(msg: String) =
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()

    private var handler = Handler(Looper.getMainLooper()){ msg ->
        if (progress1 !=0)
            ll_progress.visibility = View.VISIBLE
        if (msg.what == 1) {
            progressBar2.progress = progress1
            tv_progress.text = "$progress1%"
        }
        if (progress1 == 100) {
            ll_progress.visibility = View.GONE
            val height = ed_height.text.toString().toDouble() //身高
            val weight = ed_weight.text.toString().toDouble() //體重
            val age = ed_age.text.toString().toDouble() //年齡
            val bmi = weight / ((height / 100).pow(2)) //BMI
            val (stand_weight, body_fat) = if (btn_boy.isChecked) {
                Pair((height - 80) * 0.7, 1.39 * bmi + 0.16 * age - 19.34)
            } else {
                Pair((height - 70) * 0.6, 1.39 * bmi + 0.16 * age - 9)
            }
            tv_weight.text = "標準體重 \n${String.format("%.2f", stand_weight)}"
            tv_fat.text = "體脂肪 \n${String.format("%.2f", body_fat)}"
            tv_bmi.text = "BMI \n${String.format("%.2f", bmi)}"
        }
        true
    }

    //用 Coroutines 模擬檢測過程
    private fun runCoroutines() {
        Thread {
                while (progress1 < 100) {
                    Thread.sleep(50)
                    progress1++
                    val msg = Message()
                    msg.what =1
                    handler.sendMessage(msg)
                }


            }.start()
        }
    }

