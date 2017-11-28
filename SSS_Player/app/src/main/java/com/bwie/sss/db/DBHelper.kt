package com.bwie.sss.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHelper private constructor(context: Context) : SQLiteOpenHelper(context, DB_NAME, null, VERSION) {

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(SQL_CREATE)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL(SQL_DROP)
        db.execSQL(SQL_CREATE)
    }

    companion object {
        private val DB_NAME = "download.db"
        private val VERSION = 1
        private val SQL_CREATE = "create table thread_info(_id integer primary key autoincrement, " + "thread_id integer, url text, start integer, end integer, finished integer)"
        private val SQL_DROP = "drop table if exists thread_info"
        private var sHelper: DBHelper? = null

        /**
         * ʹ�õ���ģʽ��ȡDBHelper
         */
        fun getInstance(context: Context): DBHelper {
            if (sHelper == null) {
                sHelper = DBHelper(context)
            }
            return sHelper as DBHelper
        }
    }

}
