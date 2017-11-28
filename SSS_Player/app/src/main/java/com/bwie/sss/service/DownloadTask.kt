package com.bwie.sss.service

import android.content.Context
import android.content.Intent
import android.util.Log


import com.bwie.sss.bean.FileIn
import com.bwie.sss.bean.ThreadInfo
import com.bwie.sss.db.ThreadDAO
import com.bwie.sss.db.ThreadDAOImple

import java.io.File
import java.io.IOException
import java.io.InputStream
import java.io.RandomAccessFile
import java.net.HttpURLConnection
import java.net.URL
import java.util.ArrayList
import java.util.concurrent.Executors

class DownloadTask(comtext: Context, fileInfo: FileIn, threadCount: Int) {
    private var mComtext: Context? = null
    private var mFileInfo: FileIn? = null
    private var mDao: ThreadDAO? = null
    private var mFinished = 0
    private var mThreadCount = 1
    var mIsPause = false
    private var mThreadlist: MutableList<DownloadThread>? = null

    init {
        this.mThreadCount = threadCount
        this.mComtext = comtext
        this.mFileInfo = fileInfo
        this.mDao = ThreadDAOImple(mComtext!!)
    }

    fun download() {
        val list : ArrayList<ThreadInfo>
        list = mDao!!.queryThreads(mFileInfo!!.url!!) as ArrayList<ThreadInfo>
        if (list.size == 0) {
            val length = mFileInfo!!.length
            val block = length / mThreadCount
            for (i in 0 until mThreadCount) {
                val start = i * block
                var end = (i + 1) * block - 1
                if (i == mThreadCount - 1) {
                    end = length - 1
                }
                val threadInfo = ThreadInfo(i, mFileInfo!!.url!!, start, end, 0)
                list.add(threadInfo)
            }
        }
        mThreadlist = ArrayList()
        for (info in list) {
            val thread = DownloadThread(info)
            DownloadTask.sExecutorService.execute(thread)
            mThreadlist!!.add(thread)
            mDao!!.insertThread(info)
        }
        Log.i("test",list.size.toString())
    }

    @Synchronized
    fun checkAllFinished() {
        var allFinished = true
        for (thread in mThreadlist!!) {
            if (!thread.isFinished) {
                allFinished = false
                break
            }
        }
        if (allFinished == true) {

            mDao!!.deleteThread(mFileInfo!!.url!!)

            val intent = Intent(DownloadService.ACTION_FINISHED)
            intent.putExtra("fileInfo", mFileInfo)
            mComtext!!.sendBroadcast(intent)

        }
    }

    internal inner class DownloadThread(threadInfo: ThreadInfo) : Thread() {
        private var threadInfo: ThreadInfo? = null

        var isFinished = false

        init {
            this.threadInfo = threadInfo
        }

        override fun run() {
            Log.i("test","开始")
            var conn: HttpURLConnection? = null
            var raf: RandomAccessFile? = null
            var `is`: InputStream? = null
            try {
                val url = URL(mFileInfo!!.url)
                conn = url.openConnection() as HttpURLConnection
                conn.connectTimeout = 5 * 1000
                conn.requestMethod = "GET"

                val start = threadInfo!!.start + threadInfo!!.finished
                conn.setRequestProperty("Range", "bytes=" + start + "-" + threadInfo!!.end)
                val file = File(DownloadService.DownloadPath, mFileInfo!!.fileName!!)
                raf = RandomAccessFile(file, "rwd")
                raf.seek(start.toLong())
                mFinished += threadInfo!!.finished
                val intent = Intent()
                intent.action = DownloadService.ACTION_UPDATE
                val code = conn.responseCode
                if (code == HttpURLConnection.HTTP_PARTIAL) {
                    `is` = conn.inputStream
                    val bt = ByteArray(1024)
                    var len = -1
                    var time = System.currentTimeMillis()
                    do {
                        len = `is`!!.read(bt)
                        raf.write(bt, 0, len)

                        mFinished += len

                        threadInfo!!.finished = threadInfo!!.finished + len

                        if (System.currentTimeMillis() - time > 1000) {
                            time = System.currentTimeMillis()

                            intent.putExtra("finished", mFinished * 100 / mFileInfo!!.length)

                            intent.putExtra("id", mFileInfo!!.id)
                            Log.i("test", (mFinished * 100 / mFileInfo!!.length).toString() + "")

                            mComtext!!.sendBroadcast(intent)
                        }
                        if (mIsPause) {
                            mDao!!.updateThread(threadInfo!!.url!!, threadInfo!!.id, threadInfo!!.finished)
                            return
                        }
                    }while (len!=-1)

                }

                isFinished = true

                checkAllFinished()

            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                if (conn != null) {
                    conn.disconnect()
                }
                try {
                    if (`is` != null) {
                        `is`.close()
                    }
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

        var sExecutorService = Executors.newCachedThreadPool()
    }

}
