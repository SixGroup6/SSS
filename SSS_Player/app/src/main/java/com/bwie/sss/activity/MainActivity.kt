package com.bwie.sss.activity

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.ImageView
import com.bwie.sss.R
import com.bwie.sss.presenter.P_UpData
import com.bwie.sss.view.IView_Main
import com.jaeger.library.StatusBarUtil
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    var handle: Handler = object : Handler() {}
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        StatusBarUtil.setTranslucent(this,0)
        initData()
    }


    fun initData() {
        Log.e("123","11111")
        wel.scaleType = ImageView.ScaleType.FIT_XY
        handle.postDelayed(Runnable {
            startActivity(Intent(this@MainActivity,HomeActivity::class.java))
            finish()
        },1500)
    }

}
