package com.bwie.sss.activity

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import com.bwie.sss.R
import com.bwie.sss.adapter.VideoAdapter
import com.bwie.sss.bean.FileInfo
import com.bwie.sss.bean.FileInfoBean
import com.bwie.sss.bean.UpDataBean
import com.bwie.sss.bean.VideoBean
import com.bwie.sss.presenter.P_UpData
import com.bwie.sss.service.PlayService
import com.bwie.sss.view.IView_Main
import kotlinx.android.synthetic.main.activity_main.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode


class MainActivity : BaseActivity<IView_Main, P_UpData>(),IView_Main {
    val array=ArrayList<VideoBean.Video>()
    var videoAdapter:VideoAdapter?=null

    override fun getLayout(): Int {
        return R.layout.activity_main
    }

    override fun getPresenter(): P_UpData {
        return P_UpData()
    }

    override fun initData() {

        EventBus.getDefault().register(this)
        recycler.layoutManager=LinearLayoutManager(this )

       // presenter?.getUpData(applicationContext)
        presenter?.getloadVideo(applicationContext)
    }

    override fun setUpdata(upData: UpDataBean.UpData) {
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





