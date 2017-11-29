package com.bwie.sss.activity

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import cn.jzvd.JZVideoPlayerStandard

import com.bwie.sss.R
import com.bwie.sss.bean.VideoBean
import com.bwie.sss.util.SpUtils
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {

    lateinit var bean: VideoBean

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        bean = intent.getParcelableExtra<VideoBean>("data")
        initData()

        /*var preferences = SpUtils(this).prefs
        var islogin = preferences.getBoolean("islogin", false)
        if (islogin) {
            //详情页
            startActivity(Intent(this,DetailActivity::class.java))

        } else {
            //登录
            var intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }*/
    }
    //初始化数据
    fun initData(){
        jc_video.setUp(bean.playUrl, JZVideoPlayerStandard.SCREEN_LAYOUT_NORMAL,"")
        jc_video.thumbImageView.scaleType= ImageView.ScaleType.FIT_XY
        Picasso.with(this).load(bean.feed).into(jc_video.thumbImageView)
        tv_title.setText(bean.title)
        tv_describe.setText(bean.description)
        iv_background.scaleType=ImageView.ScaleType.FIT_XY
        Picasso.with(this).load(bean.blurred).into(iv_background)
    }
}
