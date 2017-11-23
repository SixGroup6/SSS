package com.bwie.sss.service

import android.app.Service
import android.content.Intent
import android.os.Handler
import android.os.IBinder
import android.os.Message
import android.util.Log
import com.bwie.sss.bean.FileInfoBean
import com.bwie.sss.util.DownLoadUtils
import java.io.File
import java.io.RandomAccessFile
import java.net.HttpURLConnection
import java.net.URL

/**
 * 1:类的用途
 * 2：@author Dell
 * 3：@date 2017/11/22
 */
class PlayService : Service() {
    var handler : Handler = object : Handler(){
        override fun handleMessage(msg: Message?) {
            super.handleMessage(msg)
            if (msg!!.what == 0){
                val obj = msg.obj as FileInfoBean?
                val downLoadUtils = DownLoadUtils(applicationContext, obj)
                downLoadUtils.downLoad()
            }
        }
    }
    override fun onBind(p0: Intent?): IBinder {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        //得到传过来的值
        Log.i("xxx","服务开启")
        val fileInfo : FileInfoBean
         fileInfo = intent?.getSerializableExtra("apkUrl") as FileInfoBean
        //第一次网络请求，获取文件大小
        Thread(Runnable {
            val url = URL(fileInfo.url)
            val connection = url.openConnection() as HttpURLConnection
            //获取到文件大小
            val length = connection.contentLength
            if (length < 0){
                return@Runnable
            }
            Log.i("xxx文件大小",length.toString())
            //赋值给文件信息
            fileInfo.length = length
            //创建本地文件夹
            val dir = File("/mnt/sdcard/mdownload")
            //文件夹不存在则创建
            if(!dir.exists()){
                dir.mkdir()
            }
            //创建文件
            val file = File(dir, "new.apk")
            //实例
            val randomAccessFile = RandomAccessFile(file, "rwd")
            randomAccessFile.setLength(length.toLong())
            //handler发送消息
            val message = Message()
            message.what = 0
            message.obj = fileInfo
            handler.sendMessage(message)
        }).start()
        return super.onStartCommand(intent, flags, startId)
    }
}