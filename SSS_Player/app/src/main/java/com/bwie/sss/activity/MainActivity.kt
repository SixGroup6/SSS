package com.bwie.sss.activity

import android.app.AlertDialog
import android.app.ProgressDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
<<<<<<< HEAD
import android.support.v7.widget.LinearLayoutManager
=======
import android.os.Build
import android.os.Looper
import android.support.annotation.RequiresApi
>>>>>>> 68b322bf494aaf734322c93d17eafbd78173fada
import android.util.Log
import com.bwie.sss.R
import com.bwie.sss.adapter.VideoAdapter
import com.bwie.sss.bean.FileInfo
import com.bwie.sss.bean.FileInfoBean
import com.bwie.sss.bean.UpDataBean
import com.bwie.sss.bean.VideoBean
import com.bwie.sss.presenter.P_UpData
import com.bwie.sss.service.PlayService
<<<<<<< HEAD
=======
import com.bwie.sss.util.DownLoadUtils
>>>>>>> 68b322bf494aaf734322c93d17eafbd78173fada
import com.bwie.sss.view.IView_Main
import kotlinx.android.synthetic.main.activity_main.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode


class MainActivity : BaseActivity<IView_Main, P_UpData>(),IView_Main {
<<<<<<< HEAD
    val array=ArrayList<VideoBean.Video>()
    var videoAdapter:VideoAdapter?=null

=======

    var dialog : ProgressDialog? = null

    companion object {
        var context : Context? = null
        lateinit var alert : AlertDialog
    }
>>>>>>> 68b322bf494aaf734322c93d17eafbd78173fada
    override fun getLayout(): Int {
        return R.layout.activity_main
    }

    override fun getPresenter(): P_UpData {
        return P_UpData()
    }

    override fun initData() {
<<<<<<< HEAD

        EventBus.getDefault().register(this)
        recycler.layoutManager=LinearLayoutManager(this )

       // presenter?.getUpData(applicationContext)
        presenter?.getloadVideo(applicationContext)
=======
        EventBus.getDefault().register(this)
        presenter?.getUpData(applicationContext)
        context = this
>>>>>>> 68b322bf494aaf734322c93d17eafbd78173fada
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun setUpdata(upData: UpDataBean.UpData) {
<<<<<<< HEAD
        var alert : AlertDialog
        val intent = Intent(this, PlayService::class.java)
        alert = AlertDialog.Builder(this)
                .setMessage("有新的版本可以升级~")
                .setPositiveButton("立刻升级",DialogInterface.OnClickListener { dialogInterface, i ->
                    var fileInfo : FileInfoBean
                    fileInfo = FileInfoBean()
                    fileInfo.id = 0
                    fileInfo.url = upData.apkUrl
                    //跳转服务
                    intent.putExtra("apkUrl",fileInfo)
                    startService(intent)
                })
                .setNegativeButton("以后再说",DialogInterface.OnClickListener { dialogInterface, i ->
                })
                .create()
        alert.show()
    }

    override fun setVideo(videoBean: VideoBean.Video) {
       array.add(videoBean)

        videoAdapter= VideoAdapter(this,videoBean)
        recycler.adapter=videoAdapter
        Log.i("video",videoBean.toString())
    }
    override fun onDestroy() {
        super.onDestroy()
        EventBus.getDefault().unregister(this)
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun event(fileInfo: FileInfo){

    }
}




=======
        val versionCode = packageManager.getPackageInfo(getPackageName(), 0).versionCode
        if (versionCode < upData.versionName.toInt()) {
            Looper.prepare()
            alert = AlertDialog.Builder(this)
                    .setMessage("有新的版本可以升级~")
                    .setPositiveButton("立刻升级", DialogInterface.OnClickListener { dialogInterface, i ->
                        var fileInfo: FileInfoBean
                        fileInfo = FileInfoBean()
                        fileInfo.id = 0
                        fileInfo.start = 0
                        fileInfo.url = upData.apkUrl
                        val intent = Intent(this, PlayService::class.java)
                        //跳转服务
                        intent.putExtra("apkUrl", fileInfo)
                        startService(intent)
                        alert.dismiss()
                        //对话框的进度条
                        dialog = ProgressDialog(this)
                        dialog!!.setMessage("正在下载……")
                        dialog!!.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL)
                        dialog!!.setButton("取消",DialogInterface.OnClickListener { dialogInterface, i ->
                            DownLoadUtils.isPause = true
                        })
                        dialog!!.show()
                    })
                    .setNegativeButton("以后再说", DialogInterface.OnClickListener { dialogInterface, i ->

                    })
                    .create()
            alert.show()
            Looper.loop()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        EventBus.getDefault().unregister(this)
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun event(fileInfo: FileInfo){
        dialog!!.progress = fileInfo.length!!
        Log.i("xxx",fileInfo.length!!.toString())
        if (fileInfo.length == 100){
            dialog!!.dismiss()
        }
    }

    override fun setVideo(videoBean: VideoBean.Video) {

    }
}
>>>>>>> 68b322bf494aaf734322c93d17eafbd78173fada

