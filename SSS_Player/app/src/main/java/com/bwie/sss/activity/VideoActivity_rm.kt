package com.bwie.sss.activity

import android.content.Context
import android.content.pm.ActivityInfo
import android.content.res.Configuration
import android.graphics.BitmapFactory
import android.os.AsyncTask
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bwie.sss.R
import com.bwie.sss.bean.details
import com.bwie.sss.util.ImageLoadUtils
import com.bwie.sss.util.VideoListener
import com.shuyu.gsyvideoplayer.GSYVideoPlayer
import com.shuyu.gsyvideoplayer.utils.OrientationUtils
import com.shuyu.gsyvideoplayer.video.StandardGSYVideoPlayer
import java.io.FileInputStream
import java.io.FileNotFoundException
import java.util.concurrent.ExecutionException

class VideoActivity_rm : AppCompatActivity() {
    companion object {
        var MSG_IMAGE_LOADED = 101
    }
    lateinit var bean: details
    var mContext: Context = this
    lateinit var imageView: ImageView
    var isPlay: Boolean = false
    var isPause: Boolean = false
    lateinit var orientationUtils: OrientationUtils
    lateinit var gsy_player: StandardGSYVideoPlayer
    lateinit var tv_video_time :TextView
    lateinit var tv_video_share :TextView
    lateinit var tv_video_reply :TextView
    lateinit var tv_video_download:TextView
    lateinit var tv_video_favor:TextView
    lateinit var tv_video_desc :TextView
    lateinit var tv_video_title: TextView
    lateinit var iv_bottom_bg :ImageView

    var mHandler: Handler = object : Handler() {
        override fun handleMessage(msg: Message?) {
            super.handleMessage(msg)
            when (msg?.what) {
                MSG_IMAGE_LOADED -> {
                    Log.e("video", "setImage")
                    gsy_player.setThumbImageView(imageView)
                }
            }
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_video_rm)
        bean = intent.getParcelableExtra<details>("data")
        gsy_player = findViewById(R.id.gsy_player) as StandardGSYVideoPlayer
        tv_video_time = findViewById(R.id.tv_video_time) as TextView
        tv_video_share = findViewById(R.id.tv_video_share) as TextView
        tv_video_reply = findViewById(R.id.tv_video_reply) as TextView
        tv_video_time = findViewById(R.id.tv_video_time) as TextView
        tv_video_time = findViewById(R.id.tv_video_time) as TextView
        tv_video_download = findViewById(R.id.tv_video_download) as TextView
        tv_video_favor = findViewById(R.id.tv_video_favor) as TextView
        tv_video_desc =findViewById(R.id.tv_video_desc) as TextView
        tv_video_title = findViewById(R.id.tv_video_title)as TextView
        iv_bottom_bg = findViewById(R.id.iv_bottom_bg) as ImageView
        initView()
        prepareVideo()
    }

    private fun initView() {
        var bgUrl = bean.blurred
        bgUrl?.let { ImageLoadUtils.displayHigh(this, iv_bottom_bg, bgUrl) }
        tv_video_desc.text = bean.description

        tv_video_title.text = bean.title

        var category = bean.category
        var duration = bean.duration
        var minute = duration?.div(60)
        var second = duration?.minus((minute?.times(60)) as Long)
        var realMinute: String
        var realSecond: String
        if (minute!! < 10) {
            realMinute = "0" + minute
        } else {
            realMinute = minute.toString()
        }
        if (second!! < 10) {
            realSecond = "0" + second
        } else {
            realSecond = second.toString()
        }
        tv_video_time.text = "$category / $realMinute'$realSecond''"
        tv_video_favor.text = bean.collect.toString()
        tv_video_share.text = bean.share.toString()
        tv_video_reply.text = bean.share.toString()
        tv_video_download.setOnClickListener {
            //点击下载
          /*  var url = bean.playUrl?.let { it1 -> SPUtils.getInstance(this, "downloads").getString(it1) }
            if (url.equals("")) {
                var count = SPUtils.getInstance(this, "downloads").getInt("count")
                if (count != -1) {
                    count = count.inc()
                } else {
                    count = 1
                }
                SPUtils.getInstance(this, "downloads").put("count", count)
                ObjectSaveUtils.saveObject(this, "download$count", bean)
                addMission(bean.playUrl,count)
            } else {
                showToast("该视频已经缓存过了")
            }*/
        }
    }


    /*private fun addMission(playUrl: String?, count: Int) {
        RxDownload.getInstance(this).serviceDownload(playUrl,"download$count").subscribe({
            showToast("开始下载")
            SPUtils.getInstance(this, "downloads").put(bean.playUrl.toString(),bean.playUrl.toString())
            SPUtils.getInstance(this, "download_state").put(playUrl.toString(), true)
        }, {
            showToast("添加任务失败")
        })
    }*/
    private fun prepareVideo() {
        var uri = intent.getStringExtra("URl")
        if(uri!=null){
            Log.e("uri",uri)
            gsy_player.setUp(uri, false, null, null)
        }else{
            gsy_player.setUp(bean.playUrl, false, null, null)
        }

        //增加封面
        imageView = ImageView(this)
        imageView.scaleType = ImageView.ScaleType.CENTER_CROP
        ImageViewAsyncTask(mHandler, this, imageView).execute(bean.feed)
        gsy_player.titleTextView.visibility = View.GONE

        gsy_player.backButton.visibility = View.VISIBLE
        gsy_player.initUIState()
        orientationUtils = OrientationUtils(this, gsy_player)
        gsy_player.setIsTouchWiget(true);
        //关闭自动旋转
        gsy_player.isRotateViewAuto = false;
        gsy_player.isLockLand = false;
        gsy_player.isShowFullAnimation = false;
        gsy_player.isNeedLockFull = true;
        gsy_player.fullscreenButton.setOnClickListener {
            //直接横屏
            orientationUtils.resolveByClick();
            //第一个true是否需要隐藏actionbar，第二个true是否需要隐藏statusbar
            gsy_player.startWindowFullscreen(mContext, true, true);
        }
        gsy_player.setStandardVideoAllCallBack(object : VideoListener() {
            override fun onPrepared(url: String?, vararg objects: Any?) {
                super.onPrepared(url, *objects)
                //开始播放了才能旋转和全屏
                isPlay = true;
                orientationUtils.isEnable = true

            }

            override fun onAutoComplete(url: String?, vararg objects: Any?) {
                super.onAutoComplete(url, *objects)
            }

            override fun onClickStartError(url: String?, vararg objects: Any?) {
                super.onClickStartError(url, *objects)
            }

            override fun onQuitFullscreen(url: String?, vararg objects: Any?) {
                orientationUtils?.let { orientationUtils.backToProtVideo() }
            }
        })

        gsy_player.setLockClickListener { view, lock ->
            //配合下方的onConfigurationChanged
            orientationUtils.isEnable = !lock
        }
        gsy_player.backButton.setOnClickListener(View.OnClickListener {
            onBackPressed()
        })
    }

    private class ImageViewAsyncTask(handler: Handler, activity: VideoActivity_rm, private val mImageView: ImageView) : AsyncTask<String, Void, String>() {
        private var handler = handler
        private var mPath: String? = null
        private var mIs: FileInputStream? = null
        private var mActivity: VideoActivity_rm = activity
        override fun doInBackground(vararg params: String): String? {
            val future = Glide.with(mActivity)
                    .load(params[0])
                    .downloadOnly(100, 100)
            try {
                val cacheFile = future.get()
                mPath = cacheFile.absolutePath
            } catch (e: InterruptedException) {
                e.printStackTrace()
            } catch (e: ExecutionException) {
                e.printStackTrace()
            }

            return mPath
        }

        override fun onPostExecute(s: String) {
            super.onPostExecute(s)
            try {
                mIs = FileInputStream(s)
            } catch (e: FileNotFoundException) {
                e.printStackTrace()
            }
            val bitmap = BitmapFactory.decodeStream(mIs)
            mImageView.setImageBitmap(bitmap)
            var message = handler.obtainMessage()
            message.what = MSG_IMAGE_LOADED
            handler.sendMessage(message)
        }
    }
    override fun onBackPressed() {
        orientationUtils?.let {
            orientationUtils.backToProtVideo()
        }
        if (StandardGSYVideoPlayer.backFromWindowFull(this)) {
            return
        }
        super.onBackPressed()
    }

    override fun onPause() {
        super.onPause()
        isPause = true
    }

    override fun onResume() {
        super.onResume()
        isPause = false
    }

    override fun onDestroy() {
        super.onDestroy()
        GSYVideoPlayer.releaseAllVideos()
        orientationUtils?.let {
            orientationUtils.releaseListener()
        }
    }
    override fun onConfigurationChanged(newConfig: Configuration?) {
        super.onConfigurationChanged(newConfig)
        if (isPlay && !isPause) {
            if (newConfig?.orientation == ActivityInfo.SCREEN_ORIENTATION_USER) {
                if (!gsy_player.isIfCurrentIsFullscreen) {
                    gsy_player.startWindowFullscreen(mContext, true, true)
                }
            } else {
                //新版本isIfCurrentIsFullscreen的标志位内部提前设置了，所以不会和手动点击冲突
                if (gsy_player.isIfCurrentIsFullscreen) {
                    StandardGSYVideoPlayer.backFromWindowFull(this);
                }
                orientationUtils?.let { orientationUtils.isEnable = true }
            }
        }
    }


}
