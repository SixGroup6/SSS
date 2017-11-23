package com.bwie.sss.activity

import android.app.AlertDialog
import android.content.DialogInterface
import android.util.Log
import com.bwie.sss.R
import com.bwie.sss.bean.FileInfo
import com.bwie.sss.bean.FileInfoBean
import com.bwie.sss.bean.UpDataBean
import com.bwie.sss.bean.VideoBean
import com.bwie.sss.presenter.P_UpData
import com.bwie.sss.view.IView_Main
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode


class MainActivity : BaseActivity<IView_Main, P_UpData>(),IView_Main {



    override fun getLayout(): Int {
        return R.layout.activity_main
    }

    override fun getPresenter(): P_UpData {
        return P_UpData()
    }

    override fun initData() {
<<<<<<< HEAD
        EventBus.getDefault().register(this)
        presenter?.getUpData(applicationContext)
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

    override fun onDestroy() {
        super.onDestroy()
        EventBus.getDefault().unregister(this)
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun event(fileInfo: FileInfo){

    }
}
=======
        /* recycler.adapter=*/
        //  presenter?.getUpData(this)
        presenter?.getloadVideo(this)
    }

    override fun setUpdata(upData: UpDataBean.UpData) {

        val versionCode = packageManager.getPackageInfo(packageName, 0).versionCode
        if (versionCode < upData.versionName.toInt()) {
            var alert: AlertDialog
            // val intent = Intent(this, PlayService::class.java)
            alert = AlertDialog.Builder(this)
                    .setMessage("有新的版本可以升级~")
                    .setPositiveButton("立刻升级", DialogInterface.OnClickListener { dialogInterface, i ->
                        //跳转服务
                        intent.putExtra("apkUrl", upData.apkUrl)
                        startService(intent)
                    })
                    .setNegativeButton("以后再说", DialogInterface.OnClickListener { dialogInterface, i ->
                        presenter?.getloadVideo(applicationContext)
                    })
                    .create()
            alert.show()
            return
        }
        Log.i("124", "123")
        presenter?.getloadVideo(applicationContext)
    }

    override fun setVideo(videoBean: VideoBean.Video) {
        Log.i("124", "123")
        Log.i("1", videoBean.toString())
    }

}
>>>>>>> c955cdcf4a316e0fcddb1859272c023a53e1b018
