package com.bwie.sss.fragment

import android.app.AlertDialog
import android.app.ProgressDialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Looper
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import com.bwie.sss.adapter.VideoAdapter
import com.bwie.sss.bean.FileInfo
import com.bwie.sss.bean.FileInfoBean
import com.bwie.sss.bean.UpDataBean

import com.bwie.sss.R
import com.bwie.sss.bean.*
import com.bwie.sss.presenter.P_UpData
import com.bwie.sss.service.PlayService
import com.bwie.sss.util.DownLoadUtils
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
class Fragment_sy : BaseFragment<IView_Main,P_UpData<IView_Main>>(),IView_Main, SwipeRefreshLayout.OnRefreshListener {

    var dialog: ProgressDialog? = null
    var data: String? = null
    var mIsRefresh: Boolean = false
    var mList = ArrayList<HomeBean.IssueListBean.ItemListBean>()
    var mAdapter: VideoAdapter? = null

    companion object {
        lateinit var alert: AlertDialog
    }
    override fun getPresenter(): P_UpData<IView_Main> {
        return P_UpData()
    }
    override fun getLayout(): Int {
        return R.layout.fragment_sy
    }

    override fun initData() {
        EventBus.getDefault().register(this)
        var intent = activity.intent
        var extra = intent.getBooleanExtra("login", false)
        //版本更新
        /*if (!extra) {
            presenter?.getUpData(activity.applicationContext)
        }*/
        //第一次请求
        presenter?.getFirstVideo(activity)
        recycler.layoutManager = LinearLayoutManager(context)
        mAdapter = VideoAdapter(context, mList)
        recycler.adapter = mAdapter
        vide_show.setOnClickListener {
            //跳转到搜索界面
            //startActivity(Intent(activity, CacheActivity::class.java))
        }

    }

    override fun setVideo(videoBean: HomeBean) {

        val regEx = "[^0-9]"
        val p = Pattern.compile(regEx)
        val m = p.matcher(videoBean?.nextPageUrl)
        data = m.replaceAll("").subSequence(1, m.replaceAll("").length - 1).toString()

        if (mIsRefresh) {
            mIsRefresh = false
            swip.isRefreshing = false
            if (mList.size > 0) {
                mList.clear()
            }
        }
        videoBean.issueList!!
                .flatMap { it.itemList!! }
                .filter { it.type.equals("video") }
                .forEach { mList.add(it) }
        mAdapter?.notifyDataSetChanged()

        swip.setOnRefreshListener(this)
        //加载更多
        recycler.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView?, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                var layoutManager: LinearLayoutManager = recyclerView?.layoutManager as LinearLayoutManager
                var lastPositon = layoutManager.findLastVisibleItemPosition()
                if (newState == RecyclerView.SCROLL_STATE_IDLE && lastPositon == mList.size-1) {
                    if (data != null) {
                        presenter?.getMoreVideo(activity,data)
                    }
                }
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
    override fun onRefresh() {
        if (!mIsRefresh) {
            mIsRefresh = true
            presenter?.getFirstVideo(activity)
        }
    }
}