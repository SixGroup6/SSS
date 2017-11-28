package com.bwie.sss.activity

import android.content.Intent
import android.os.Handler
import android.util.Log
import android.widget.ImageView
import com.bwie.sss.R
import com.bwie.sss.presenter.P_UpData
import com.bwie.sss.view.IView_Main
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity<IView_Main, P_UpData<Any?>>() {
    var handle: Handler = object : Handler() {}
    override fun getLayout(): Int {
        return R.layout.activity_main
    }

    override fun getPresenter(): P_UpData<Any?>? {
        return null
    }

    override fun initData() {
        Log.e("123","11111")
        wel.scaleType = ImageView.ScaleType.FIT_XY
        handle.postDelayed(Runnable {
            startActivity(Intent(this@MainActivity,HomeActivity::class.java))
            finish()
        },3000)
    }

}
