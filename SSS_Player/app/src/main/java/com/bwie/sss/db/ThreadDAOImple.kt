package com.bwie.sss.db

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase


import com.bwie.sss.bean.ThreadInfo

import java.util.ArrayList

/**
 * ���������h�Ĳ�Č��F�
 *
 */
class ThreadDAOImple(context: Context) : ThreadDAO {
    private var dbHelper: DBHelper? = null

    init {
        this.dbHelper = DBHelper.getInstance(context)
    }

    // ����Q��
    @Synchronized override fun insertThread(info: ThreadInfo) {
        val db = dbHelper!!.readableDatabase
        val values = ContentValues()
        values.put("thread_id", info.id)
        values.put("url", info.url)
        values.put("start", info.start)
        values.put("end", info.end)
        values.put("finished", info.finished)
        db.insert("thread_info", null, values)

        db.close()
    }

    // �h���Q��
    @Synchronized override fun deleteThread(url: String) {
        val db = dbHelper!!.readableDatabase
        db.delete("thread_info", "url = ?", arrayOf(url))

        db.close()

    }

    // ���¾Q��
    @Synchronized override fun updateThread(url: String, thread_id: Int, finished: Int) {
        val db = dbHelper!!.readableDatabase

        db.execSQL("update thread_info set finished = ? where url = ? and thread_id = ?",
                arrayOf(finished, url, thread_id))
        db.close()
    }

    // ��ԃ�Q��
    override fun queryThreads(url: String): List<ThreadInfo> {
        val db = dbHelper!!.readableDatabase

        val list = ArrayList<ThreadInfo>()

        val cursor = db.query("thread_info", null, "url = ?", arrayOf(url), null, null, null)
        while (cursor.moveToNext()) {
            val thread = ThreadInfo()
            thread.id = cursor.getInt(cursor.getColumnIndex("thread_id"))
            thread.url = cursor.getString(cursor.getColumnIndex("url"))
            thread.start = cursor.getInt(cursor.getColumnIndex("start"))
            thread.end = cursor.getInt(cursor.getColumnIndex("end"))
            thread.finished = cursor.getInt(cursor.getColumnIndex("finished"))
            list.add(thread)
        }


        cursor.close()
        db.close()
        return list
    }

    // �Д�Q���Ƿ񠑿�
    override fun isExists(url: String, thread_id: Int): Boolean {
        val db = dbHelper!!.readableDatabase
        val cursor = db.query("thread_info", null, "url = ? and thread_id = ?", arrayOf(url, thread_id.toString() + ""), null, null, null)
        val exists = cursor.moveToNext()

        db.close()
        cursor.close()
        return exists
    }

}
