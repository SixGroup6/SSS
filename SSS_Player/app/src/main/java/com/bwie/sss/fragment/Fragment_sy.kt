package com.bwie.sss.fragment

import android.app.AlertDialog
import android.app.ProgressDialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Handler
import android.os.Looper
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import com.bawei.swiperefreshlayoutlibrary.SwipyRefreshLayout
import com.bawei.swiperefreshlayoutlibrary.SwipyRefreshLayoutDirection
import com.bwie.sss.R
import com.bwie.sss.activity.LoginActivity
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
import kotlinx.android.synthetic.main.fragment_sy.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import java.util.*
import java.util.regex.Pattern

/**
 * 1:类的用途
 * 2：@author Dell
 * 3：@date 2017/11/27
 */
class Fragment_sy : BaseFragment<IView_Main,P_UpData<IView_Main>>(),IView_Main{
    override fun getPresenter(): P_UpData<IView_Main> {
        return P_UpData()
    }

    var handle: Handler = object : Handler() {}
    var array = ArrayList<VideoBean.Video>()
    var videoAdapter: VideoAdapter? = null
    var dialog: ProgressDialog? = null
    var data: String? = null

    companion object {
        lateinit var alert: AlertDialog
    }

    override fun getLayout(): Int {
        return R.layout.fragment_sy
    }

    override fun initData() {
        EventBus.getDefault().register(this)
        recycler.layoutManager = LinearLayoutManager(activity,LinearLayoutManager.VERTICAL,false)
        presenter?.getloadVideo(activity.applicationContext)
        var intent = activity.intent
        var extra = intent.getBooleanExtra("login", false)
        //版本更新
        /*if (!extra) {
            presenter?.getUpData(activity.applicationContext)
        }*/
        swipy.direction = SwipyRefreshLayoutDirection.BOTH
        recycler.layoutManager = LinearLayoutManager(activity)
        vide_show.setOnClickListener {
            //跳转到搜索界面
            //startActivity(Intent(activity, CacheActivity::class.java))
        }

    }

    override fun setVideo(videoBean: VideoBean.Video) {
        array.add(videoBean)
        videoAdapter = VideoAdapter(activity, videoBean)
        recycler.adapter = videoAdapter

        val regEx = "[^0-9]"
        val p = Pattern.compile(regEx)
        val m = p.matcher(videoBean?.nextPageUrl)
        data = m.replaceAll("").subSequence(1, m.replaceAll("").length - 1).toString()
        videoAdapter!!.setOniteClickListener(object : VideoAdapter.OnItemClickLitener {
            override fun downloadLisener(pos: Int) {
                var preferences = SpUtils(activity).prefs
                var islogin = preferences.getBoolean("islogin", false)
                if (islogin) {
                    //下载视频
                } else {
                    //登录
                    var intent = Intent(activity, LoginActivity::class.java)
                    startActivity(intent)
                }
            }
        })
        swipy.setOnRefreshListener(object : SwipyRefreshLayout.OnRefreshListener {
            override fun onRefresh(index: Int) {}
            override fun onLoad(index: Int) {
                handle.postAtTime(Runnable {
                    for (i in videoBean.issueList) {
                        presenter?.getloadVideoEnd(activity, data!!)
                        swipy.isRefreshing = false
                    }
                }, 5000)
            }
        })
    }

    override fun setUpdata(upData: UpDataBean.UpData) {
        val versionCode = activity.packageManager.getPackageInfo(activity.getPackageName(), 0).versionCode
        if (versionCode < upData.versionName.toInt()) {
            Looper.prepare()
            alert = AlertDialog.Builder(activity)
                    .setMessage("有新的版本可以升级~")
                    .setPositiveButton("立刻升级", DialogInterface.OnClickListener { dialogInterface, i ->
                        var fileInfo: FileInfoBean
                        fileInfo = FileInfoBean()
                        fileInfo.id = 0
                        fileInfo.start = 0
                        fileInfo.url = upData.apkUrl
                        val intent = Intent(activity, PlayService::class.java)
                        //跳转服务
                        intent.putExtra("apkUrl", fileInfo)
                        activity.startService(intent)
                        alert.dismiss()
                        //对话框的进度条
                        dialog = ProgressDialog(activity)
                        dialog!!.setMessage("正在下载……")
                        dialog!!.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL)
                        dialog!!.setButton("取消", DialogInterface.OnClickListener { dialogInterface, i ->
                            DownLoadUtils.isPause = true
                            //presenter!!.getloadVideo(activity.applicationContext)
                        })
                        dialog!!.show()
                    })
                    .setNegativeButton("以后再说", DialogInterface.OnClickListener { dialogInterface, i ->
                        //presenter!!.getloadVideo(activity.applicationContext)
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
    fun event(fileInfo: FileInfo) {
        dialog!!.progress = fileInfo.length!!
        Log.i("xxx", fileInfo.length!!.toString())
        if (fileInfo.length == 100) {
            dialog!!.dismiss()
        }
    }
}