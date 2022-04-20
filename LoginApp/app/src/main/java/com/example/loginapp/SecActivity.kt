package com.example.loginapp

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.widget.Button
import android.widget.DatePicker
import android.widget.TextView
import android.widget.TimePicker
import androidx.appcompat.app.AppCompatActivity
import java.util.*


class SecActivity:AppCompatActivity(), DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {

    var day = 0
    var month = 0
    var year = 0
    var hour = 0
    var minute = 0

    var savedDay = 0
    var savedMonth = 0
    var savedYear = 0
    var savedHour = 0
    var savedMinute = 0
    lateinit var btn_timePicker : Button
    lateinit var tv_textTime : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sec)
        btn_timePicker = findViewById(R.id.btn_timePicker)
        tv_textTime = findViewById(R.id.tv_textTime)

        pickDate()

    }

    private fun getDateTimeCalendar(){
        val cal:Calendar = Calendar.getInstance()
        day = cal.get(Calendar.DAY_OF_MONTH)
        month = cal.get(Calendar.MONTH)
        year = cal.get(Calendar.YEAR)
        hour = cal.get(Calendar.HOUR)
        minute = cal.get(Calendar.MINUTE)
    }

    private fun pickDate() {

        btn_timePicker.setOnClickListener {
            getDateTimeCalendar()

            DatePickerDialog(this,this,year,month,day).show()
        }

    }

    override fun onDateSet(view: DatePicker?, year: Int,month: Int, dayOfMonth: Int) {
        savedDay = dayOfMonth
        savedMonth = month
        savedYear = year

        getDateTimeCalendar()

        TimePickerDialog(this,this,hour,minute,true).show()
    }

    override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {
        savedHour = hourOfDay
        savedMinute = minute

        tv_textTime.text = "$savedDay-$savedMonth-$savedYear\n Hour: $savedHour Minute: $savedMinute"

    }

}