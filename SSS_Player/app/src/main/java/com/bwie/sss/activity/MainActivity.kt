package com.bwie.sss.activity

import android.app.AlertDialog
import android.app.ProgressDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.os.Build
import android.os.Handler
import android.os.Looper
import android.support.annotation.RequiresApi
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.widget.Toast
import com.bawei.swiperefreshlayoutlibrary.SwipyRefreshLayout
import com.bawei.swiperefreshlayoutlibrary.SwipyRefreshLayoutDirection
import com.bwie.sss.R
import com.bwie.sss.adapter.VideoAdapter
import com.bwie.sss.bean.FileInfo
import com.bwie.sss.bean.FileInfoBean
import com.bwie.sss.bean.UpDataBean
import com.bwie.sss.bean.VideoBean
import com.bwie.sss.presenter.P_UpData
import com.bwie.sss.service.PlayService
import com.bwie.sss.util.DownLoadUtils
import com.bwie.sss.util.SpUtils
import com.bwie.sss.view.IView_Main
import kotlinx.android.synthetic.main.activity_main.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import java.util.*
import java.util.regex.Pattern


class MainActivity : BaseActivity<IView_Main, P_UpData>(),IView_Main {
    var handle:Handler=object:Handler(){};
    val array=ArrayList<VideoBean.Video>()
    var videoAdapter: VideoAdapter?=null
    var dialog : ProgressDialog? = null
    var data:String?=null;


    companion object {
        var context: Context? = null
        lateinit var alert: AlertDialog
    }

    override fun getLayout(): Int {
        return R.layout.activity_main
    }

    override fun getPresenter(): P_UpData {
        return P_UpData()
    }

    override fun initData() {
        val intent = intent
        val extra = intent.getBooleanExtra("login", false)
        if (extra) {
            presenter?.getloadVideo(applicationContext)
        } else {
            presenter?.getUpData(applicationContext)
        }
        EventBus.getDefault().register(this)
        recycler.layoutManager= LinearLayoutManager(this )
        presenter?.getUpData(applicationContext)
       // presenter?.getloadVideo(applicationContext)
        context = this
        vide_show.setOnClickListener{
        //    startActivity(Intent(this@MainActivity,CacheActivity::class.java))
        }
        swipy.setDirection(SwipyRefreshLayoutDirection.BOTH)
        recycler.layoutManager = LinearLayoutManager(this)
        context = this
        vide_show.setOnClickListener {
            startActivity(Intent(this@MainActivity, CacheActivity::class.java))
        }

    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun setUpdata(upData: UpDataBean.UpData) {
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
                        dialog!!.setButton("取消", DialogInterface.OnClickListener { dialogInterface, i ->
                            DownLoadUtils.isPause = true
                            presenter!!.getloadVideo(application)
                        })
                        dialog!!.show()
                    })
                    .setNegativeButton("以后再说", DialogInterface.OnClickListener { dialogInterface, i ->
                        presenter!!.getloadVideo(application)
                    })
                    .create()
            alert.show()
            Looper.loop()
        }
    }

<<<<<<< HEAD
    override fun setVideo(videoBean: VideoBean.Video) {
        array.add(videoBean)
        videoAdapter = VideoAdapter(this, videoBean)
        recycler.adapter = videoAdapter
        Log.i("xxx", videoBean.toString())
        videoAdapter!!.setOniteClickListener(object : VideoAdapter.OnItemClickLitener {

            override fun downloadLisener(pos: Int) {
                val preferences = SpUtils(this@MainActivity).prefs
                val islogin = preferences.getBoolean("islogin", false)
                if (islogin) {
                    var intent_cache=Intent(this@MainActivity,CacheActivity::class.java);
                    startActivity(intent_cache)
                } else {
				    //登录
                    var intent = Intent(this@MainActivity, LoginActivity::class.java)
                    startActivity(intent)
                }
            }
        })
=======
>>>>>>> d39631c77dead464a96f47235b48b73a324ce8df

    override fun setVideo( videoBean: VideoBean.Video) {
       array.add(videoBean)
        videoAdapter= VideoAdapter(this,videoBean)
        recycler.adapter=videoAdapter

        val regEx = "[^0-9]"
        val p = Pattern.compile(regEx)
        val m = p.matcher(videoBean?.nextPageUrl)
        data = m.replaceAll("").subSequence(1, m.replaceAll("").length - 1).toString()

                videoAdapter!!.setOniteClickListener(object : VideoAdapter.OnItemClickLitener {

                    override fun downloadLisener(pos: Int) {
                        val preferences = SpUtils(this@MainActivity).prefs
                        val islogin = preferences.getBoolean("islogin", false)
                        if (islogin) {
                            Toast.makeText(this@MainActivity, "跳转 ,第" + (pos) + "条", Toast.LENGTH_SHORT).show()
                        } else {
                            //登录
                            var intent = Intent(this@MainActivity, LoginActivity::class.java)
                            startActivity(intent)
                        }
                    }
                })
                swipy.setOnRefreshListener(object : SwipyRefreshLayout.OnRefreshListener {
                    override fun onRefresh(index: Int) {
                    }
                    override fun onLoad(index: Int) {
                        handle.postAtTime(Runnable {
                            for (i in videoBean.issueList) {
                                presenter?.getloadVideoEnd(this@MainActivity, data!!)
                                swipy.setRefreshing(false)
                            }
                        }, 5000)
                    }

                })
            }

            override fun onDestroy() {
                super.onDestroy()
                EventBus.getDefault().unregister(this)
            }


            @Subscribe(threadMode = ThreadMode.MAIN)
            fun event(fileInfo: FileInfo) {
                dialog!!.progress = fileInfo.length!!
                Log.i("xxx", fileInfo.length!!.toString())
                if (fileInfo.length == 100) {
                    dialog!!.dismiss()
                }
            }

         }





