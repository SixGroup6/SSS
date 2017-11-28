package com.bwie.sss.activity

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.ListView
import com.bwie.sss.R
import com.bwie.sss.adapter.FileAdapter
import com.bwie.sss.bean.FileIn
import com.bwie.sss.service.DownloadService
import com.bwie.sss.util.NotificationUtil
import java.util.ArrayList
import android.support.v7.widget.LinearLayoutManager
import android.view.View


import com.bwie.sss.bean.VideoBean
import kotlinx.android.synthetic.main.activity_cache.*
import zlc.season.rxdownload2.RxDownload


class CacheActivity : AppCompatActivity() {
    private var listView: ListView? = null
    private var mFileList: MutableList<FileIn>? = null
    private var mAdapter: FileAdapter? = null
    private var mNotificationUtil: NotificationUtil? = null
    private val urlone = "http://study.163.com/pub/study-android-official.apk"
    private val urltwo = "http://pic.ibaotu.com/00/38/90/13h888piC7kG.mp4_10s.mp4"
    private val urlthree = "http://s1.music.126.net/download/android/CloudMusic_3.4.1.133604_official.apk"
    private val urlfour = "http://study.163.com/pub/study-android-official.apk"


    private var mRecive: UIRecive? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cache)
        // 初始化控件
        listView = findViewById(R.id.list_view) as ListView?
        mFileList = ArrayList<FileIn>()

        // 初始化文件对象
        val fileInfo1 = FileIn(0, urlone, getfileName(urlone), 0, 0)
        val fileInfo2 = FileIn(1, urltwo, getfileName(urltwo), 0, 0)
        val fileInfo3 = FileIn(2, urlthree, getfileName(urlthree), 0, 0)
        val fileInfo4 = FileIn(3, urlfour, getfileName(urlfour), 0, 0)

        (mFileList as ArrayList<FileIn>).add(fileInfo1)
        (mFileList as ArrayList<FileIn>).add(fileInfo2)
        (mFileList as ArrayList<FileIn>).add(fileInfo3)
        (mFileList as ArrayList<FileIn>).add(fileInfo4)

        mAdapter = FileAdapter(this, mFileList as ArrayList<FileIn>)

        listView!!.setAdapter(mAdapter)
        mNotificationUtil = NotificationUtil(this)
        mRecive = UIRecive()
        val intentFilter = IntentFilter()
        intentFilter.addAction(DownloadService.ACTION_UPDATE)
        intentFilter.addAction(DownloadService.ACTION_FINISHED)
        intentFilter.addAction(DownloadService.ACTION_START)
        registerReceiver(mRecive, intentFilter)
    }

    override fun onDestroy() {
        unregisterReceiver(mRecive)
        super.onDestroy()
    }

    private fun getfileName(url: String): String {
        return url.substring(url.lastIndexOf("/") + 1)
    }

    internal inner class UIRecive : BroadcastReceiver() {

        override fun onReceive(context: Context, intent: Intent) {
            if (DownloadService.ACTION_UPDATE.equals(intent.action)) {
                // 更新进度条的时候
                val finished = intent.getIntExtra("finished", 0)
                val id = intent.getIntExtra("id", 0)
                mAdapter!!.updataProgress(id, finished)
                mNotificationUtil!!.updataNotification(id, finished)
            } else if (DownloadService.ACTION_FINISHED.equals(intent.action)) {
                // 下载结束的时候
                val fileInfo = intent.getSerializableExtra("fileInfo") as FileIn
                mAdapter!!.updataProgress(fileInfo.id, 0)
                // 下载结束后取消通知
                mNotificationUtil!!.cancelNotification(fileInfo.id)
            } else if (DownloadService.ACTION_START.equals(intent.action)) {
                // 下载开始的时候启动通知栏
                mNotificationUtil!!.showNotification(intent.getSerializableExtra("fileInfo") as FileIn)

            }
        }

    }
}
