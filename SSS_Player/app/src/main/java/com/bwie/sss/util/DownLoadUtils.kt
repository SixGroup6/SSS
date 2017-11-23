package com.bwie.sss.util

import android.content.Context
import android.util.Log
import com.bwie.sss.app.MyApp
import com.bwie.sss.bean.FileInfoBean
import com.bwie.sss.greendao.FileInfoBeanDao
import java.io.File
import java.io.RandomAccessFile
import java.net.HttpURLConnection
import java.net.URL

/**
 * 1:类的用途
 * 2：@author Dell
 * 3：@date 2017/11/23
 */
class DownLoadUtils {
    var context : Context? = null
    var fileInfo : FileInfoBean? = null

    companion object {
        var isPause : Boolean = false
        var dao : FileInfoBeanDao? = null
    }

    constructor(context: Context?, fileInfo: FileInfoBean?) {
        this.context = context
        this.fileInfo = fileInfo
        dao = MyApp.getInstance().getDataSession().fileInfoBeanDao
    }

    fun downLoad(){
        val flist = dao?.queryBuilder()?.where(FileInfoBeanDao.Properties.Url.eq(fileInfo?.getUrl()))?.build()?.list()
        if (flist!!.size == 0){
            //第一次下载  将下载信息存到数据库
            dao!!.insert(fileInfo)
            //开启线程下载
            MyThread(fileInfo).start()
        }else{
            //开启线程继续下载
            MyThread(flist.get(0)).start()
        }
    }
    class MyThread(var fileInfo : FileInfoBean? = null) : Thread(){
        var finshed :Int = 0
        override fun run() {
            super.run()
            val url = URL(fileInfo!!.url)
            val connection = url.openConnection() as HttpURLConnection
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(3000);
            connection.setReadTimeout(3000);

            //开始下载的位置
            var start : Int = fileInfo!!.start+ fileInfo!!.now
            //从指定位置开始请求
            connection.setRequestProperty("Range","bytes=" + start + "-" + fileInfo!!.getLength())
            //设置文件写入位置
            val file = File("/mnt/sdcard/mdownload", fileInfo!!.url.split("\\/")[0])
            val randomAccessFile = RandomAccessFile(file, "rwd")
            randomAccessFile.seek(start.toLong())
            //更新下载进度
            finshed = fileInfo!!.now
            if (connection.responseCode == 206){
                //获得流
                val inputStream = connection.inputStream
                var byte = ByteArray(1024)
                var length : Int = -1
                var time = System.currentTimeMillis()
                while ( inputStream.read(byte).apply { length } != -1){
                    //写入文件
                    randomAccessFile.write(byte,0,length)
                    finshed += length
                    //间隔更新进度条
                    if (System.currentTimeMillis() - time > 500){
                        time = System.currentTimeMillis()
                    }
                    if (isPause){
                        val fileInfoBean = dao?.queryBuilder()!!.where(FileInfoBeanDao.Properties.Url.eq(fileInfo!!.getUrl())).build().unique();
                        fileInfoBean.now = finshed
                        dao!!.update(fileInfoBean)
                        return
                    }
                }
                //下载完成  删除
                val unique = dao?.queryBuilder()!!.where(FileInfoBeanDao.Properties.Url.eq(fileInfo!!.getUrl())).build().unique()
                if (unique != null){
                    Log.e("xxx","数据库删除成功")
                    dao!!.delete(unique)
                }
            }
        }
    }
}