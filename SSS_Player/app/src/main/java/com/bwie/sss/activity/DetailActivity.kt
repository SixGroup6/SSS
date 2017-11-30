package com.bwie.sss.activity

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import cn.jzvd.JZVideoPlayerStandard

import com.bwie.sss.R
import com.bwie.sss.bean.VideoBean
import com.bwie.sss.util.ObjectSaveUtils
import com.bwie.sss.util.SUtils
import com.bwie.sss.util.SpUtils
import com.jaeger.library.StatusBarUtil
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail.*
import zlc.season.rxdownload2.RxDownload

class DetailActivity : AppCompatActivity() {

    lateinit var bean: VideoBean

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        StatusBarUtil.setTranslucent(this,0)
        initData()
        setListener()

    }
    //初始化数据
    fun initData(){
        bean = intent.getParcelableExtra<VideoBean>("data")
        jc_video.setUp(bean?.playUrl, JZVideoPlayerStandard.SCREEN_LAYOUT_NORMAL,"")
        jc_video.thumbImageView.scaleType= ImageView.ScaleType.FIT_XY
        Picasso.with(this).load(bean?.feed).into(jc_video.thumbImageView)
        tv_title.setText(bean?.title)
        tv_describe.setText(bean?.description)
        iv_background.scaleType=ImageView.ScaleType.FIT_XY
        Picasso.with(this).load(bean?.blurred).into(iv_background)
    }
    //点击事件
    fun setListener(){
        var preferences = SpUtils(this).prefs
        ll_down.setOnClickListener(View.OnClickListener {
            var islogin = preferences.getBoolean("islogin", false)
        if (islogin) {
           //下载
            downVideo()
        } else {
            //登录
            var intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
        })
        iv_return.setOnClickListener{
            finish()
        }
    }
    //下载
    fun downVideo(){
        //点击下载
        var url = bean.playUrl?.let { it1 -> SUtils.getInstance(this, "downloads").getString(it1) }
        if (url.equals("")) {
            var count = SUtils.getInstance(this, "downloads").getInt("count")
            if (count != -1) {
                count = count.inc()
            } else {
                count = 1
            }
            SUtils.getInstance(this, "downloads").put("count", count)
            ObjectSaveUtils.saveObject(this, "download$count", bean)
            addMission(bean.playUrl,count)
        } else {
            Toast.makeText(this@DetailActivity,"该视频已经缓存过了",0).show()
        }
    }
    private fun addMission(playUrl: String?, count: Int) {
        RxDownload.getInstance(this).serviceDownload(playUrl,"download$count").subscribe({
            Toast.makeText(this@DetailActivity,"开始下载",0).show()
            SUtils.getInstance(this, "downloads").put(bean.playUrl.toString(),bean.playUrl.toString())
            SUtils.getInstance(this, "download_state").put(playUrl.toString(), true)
        }, {
            Toast.makeText(this@DetailActivity,"添加任务失败",0).show()
        })
    }
}
