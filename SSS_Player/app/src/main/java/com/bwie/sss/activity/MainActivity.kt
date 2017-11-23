package com.bwie.sss.activity

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.util.Log
import com.bwie.sss.R
import com.bwie.sss.bean.UpDataBean
import com.bwie.sss.bean.VideoBean
import com.bwie.sss.presenter.P_UpData
import com.bwie.sss.view.IView_Main



class MainActivity : BaseActivity<IView_Main,P_UpData>(),IView_Main {
    override fun setVideo(videoBean:VideoBean.Video) {
        Log.i("124", "123")
        Log.i("1", videoBean.toString())
    }


        override fun getLayout(): Int {
        return R.layout.activity_main
    }

    override fun getPresenter(): P_UpData {
        return P_UpData()
    }

    override fun initData() {
       /* recycler.adapter=*/
     //  presenter?.getUpData(this)
        presenter?.getloadVideo(this)
    }

    override fun setUpdata(upData: UpDataBean.UpData) {
        val versionCode = packageManager.getPackageInfo(packageName, 0).versionCode
        if (versionCode < upData.versionName.toInt()){
            var alert : AlertDialog
            // val intent = Intent(this, PlayService::class.java)
            alert = AlertDialog.Builder(this)
                    .setMessage("有新的版本可以升级~")
                    .setPositiveButton("立刻升级",DialogInterface.OnClickListener { dialogInterface, i ->
                        //跳转服务
                        intent.putExtra("apkUrl",upData.apkUrl)
                        startService(intent)
                    })
                    .setNegativeButton("以后再说",DialogInterface.OnClickListener { dialogInterface, i ->
                        presenter?.getloadVideo(applicationContext)
                    })
                    .create()
            alert.show()
            return
        }
        Log.i("124","123")
        presenter?.getloadVideo(applicationContext)
    }

}
