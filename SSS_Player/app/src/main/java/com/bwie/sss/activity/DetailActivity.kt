package com.bwie.sss.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import cn.jzvd.JZVideoPlayerStandard

import com.bwie.sss.R
import com.squareup.picasso.Picasso

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
       /* holder.videoplay.setUp(video.issueList[0].itemList[position].data.playUrl, JZVideoPlayerStandard.SCREEN_LAYOUT_NORMAL,video.issueList[0].itemList[position].data.title)
        holder.videoplay.thumbImageView.scaleType= ImageView.ScaleType.FIT_XY
        Picasso.with(context).load(video.issueList[0].itemList[position].data.cover.feed).into(holder.videoplay.thumbImageView)*/
    }
}
