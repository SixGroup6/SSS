package com.bwie.sss.util

import android.app.Notification
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.widget.RemoteViews

import com.bwie.sss.R
import com.bwie.sss.activity.MainActivity
import com.bwie.sss.bean.FileIn
import com.bwie.sss.service.DownloadService

import java.util.HashMap

class NotificationUtil(private val mContext: Context) {
    private var mNotificationManager: NotificationManager? = null
    private var mNotifications: MutableMap<Int, Notification>? = null

    init {

        mNotificationManager = mContext.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        mNotifications = HashMap()
    }

    fun showNotification(fileInfo: FileIn) {

        if (!mNotifications!!.containsKey(fileInfo.id)) {
            val notification = Notification()
            notification.tickerText = fileInfo.fileName!! + "新版本"
            notification.`when` = System.currentTimeMillis()
            notification.icon = R.mipmap.ic_launcher
            notification.flags = Notification.FLAG_AUTO_CANCEL


            val intent = Intent(mContext, MainActivity::class.java)
            val pd = PendingIntent.getActivity(mContext, 0, intent, 0)
            notification.contentIntent = pd


            val remoteViews = RemoteViews(mContext.packageName, R.layout.notification)

            val intentStart = Intent(mContext, DownloadService::class.java)
            intentStart.action = DownloadService.ACTION_START
            intentStart.putExtra("fileInfo", fileInfo)
            val piStart = PendingIntent.getService(mContext, 0, intentStart, 0)
            remoteViews.setOnClickPendingIntent(R.id.start_button, piStart)


            val intentStop = Intent(mContext, DownloadService::class.java)
            intentStop.action = DownloadService.ACTION_STOP
            intentStop.putExtra("fileInfo", fileInfo)
            val piStop = PendingIntent.getService(mContext, 0, intentStop, 0)
            remoteViews.setOnClickPendingIntent(R.id.stop_button, piStop)

            remoteViews.setTextViewText(R.id.file_textview, fileInfo.fileName)

            notification.contentView = remoteViews

            mNotificationManager!!.notify(fileInfo.id, notification)

            mNotifications!!.put(fileInfo.id, notification)
        }
    }

    fun cancelNotification(id: Int) {
        mNotificationManager!!.cancel(id)
        mNotifications!!.remove(id)
    }

    fun updataNotification(id: Int, progress: Int) {
        val notification = mNotifications!![id]
        if (notification != null) {

            notification.contentView.setProgressBar(R.id.progressBar2, 100, progress, false)
            mNotificationManager!!.notify(id, notification)
        }
    }
}
