package com.bwie.sss.service

import android.app.Service
import android.content.Intent
import android.os.Environment
import android.os.Handler
import android.os.IBinder
import android.os.Message
import android.util.Log

import com.bwie.sss.bean.FileIn

import java.io.File
import java.io.IOException
import java.io.RandomAccessFile
import java.net.HttpURLConnection
import java.net.URL
import java.util.LinkedHashMap

class DownloadService : Service() {

    private val mTasks = LinkedHashMap<Int, DownloadTask>()

    internal var mHandler: Handler = object : Handler() {
        override fun handleMessage(msg: Message) {
            when (msg.what) {
                MSG_INIT -> {
                    val fileInfo = msg.obj as FileIn
                    Log.i("test", "INIT:" + fileInfo.toString())
                    val task = DownloadTask(this@DownloadService, fileInfo, 3)
                    task.download()

                    mTasks.put(fileInfo.id, task)

                    val intent = Intent(ACTION_START)
                    intent.putExtra("fileInfo", fileInfo)
                    sendBroadcast(intent)
                }
            }
        }
    }

    override fun onBind(intent: Intent): IBinder? {
        return null
    }

    override fun onCreate() {
        super.onCreate()
    }

    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
        Log.i("test", "START")
        if (ACTION_START == intent.action) {
            val fileInfo = intent.getSerializableExtra("fileInfo") as FileIn
            Log.i("test", "START" + fileInfo.toString())
            val initThread = InitThread(fileInfo)
            DownloadTask.sExecutorService.execute(initThread)
        } else if (ACTION_STOP == intent.action) {
            val fileInfo = intent.getSerializableExtra("fileInfo") as FileIn
            val task = mTasks[fileInfo.id]
            if (task != null) {

                task.mIsPause = true
            }
        }
        return super.onStartCommand(intent, flags, startId)
    }


    internal inner class InitThread(mFileInfo: FileIn) : Thread() {
        private var mFileInfo: FileIn? = null

        init {
            this.mFileInfo = mFileInfo
        }

        override fun run() {
            var conn: HttpURLConnection? = null
            var raf: RandomAccessFile? = null
            try {
                val url = URL(mFileInfo!!.url)
                conn = url.openConnection() as HttpURLConnection
                conn.connectTimeout = 5 * 1000
                conn.requestMethod = "GET"
                val code = conn.responseCode
                var length = -1
                if (code == HttpURLConnection.HTTP_OK) {
                    length = conn.contentLength
                }

                if (length <= 0) {
                    return
                }

                val dir = File(DownloadPath)
                if (!dir.exists()) {
                    dir.mkdir()
                }

                val file = File(dir, mFileInfo!!.fileName)
                raf = RandomAccessFile(file, "rwd")
                raf.setLength(length.toLong())

                mFileInfo!!.length = length

                val msg = Message.obtain()
                msg.obj = mFileInfo
                msg.what = MSG_INIT
                mHandler.sendMessage(msg)

            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                if (conn != null) {
                    conn.disconnect()
                }
                try {
                    if (raf != null) {
                        raf.close()
                    }
                } catch (e: IOException) {
                    e.printStackTrace()
                }

            }
            super.run()
        }
    }

    companion object {

        val ACTION_START = "ACTION_START"
        val ACTION_STOP = "ACTION_STOP"
        val ACTION_UPDATE = "ACTION_UPDATE"
        val ACTION_FINISHED = "ACTION_FINISHED"

        val DownloadPath = Environment.getExternalStorageDirectory().absolutePath + "/download/"
        val MSG_INIT = 0
    }

}