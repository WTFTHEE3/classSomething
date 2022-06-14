package com.example.sqlite_loginapp

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
//自訂建構子，並通過SQLiteOpenHelper類別來對SQLite資料庫進行操作
class MyDBHelper (context: Context) : SQLiteOpenHelper(context,"USERDB",null,1){
    override fun onCreate(db: SQLiteDatabase?) {
        //建立資料表USERS，並包含一個帳號、一個密碼的字串欄位
        db?.execSQL("CREATE TABLE USERS(USERID INTEGER PRIMARY KEY AUTOINCREMENT, UNAME TEXT, PWD TEXT)")
        //新增2筆帳號密碼
        db?.execSQL("INSERT INTO USERS(UNAME,PWD) VALUES('TEST@TEST1.com.tw', '123')")
        db?.execSQL("INSERT INTO USERS(UNAME,PWD) VALUES('123@456.789','132465')")
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {

    }
}