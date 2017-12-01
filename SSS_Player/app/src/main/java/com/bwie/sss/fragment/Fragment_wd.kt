package com.bwie.sss.fragment

import android.content.Intent
import android.view.View
import com.bwie.sss.R
import com.bwie.sss.activity.CacheActivity
import com.bwie.sss.activity.LoginActivity
import com.bwie.sss.presenter.P_UpData
import com.bwie.sss.util.SpUtils
import com.bwie.sss.view.IView_Main
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.fragment_wd.*

/**
 * 1:类的用途
 * 2：@author Dell
 * 3：@date 2017/11/27
 */
class Fragment_wd : BaseFragment<IView_Main,P_UpData<IView_Main>>(){
    override fun initData() {
        tv_save.setOnClickListener{
            var preferences = SpUtils(activity).prefs
            tv_save.setOnClickListener(View.OnClickListener {
                var islogin = preferences.getBoolean("islogin", false)
                if (islogin) {
                    //查看缓存视频
                    startActivity(Intent(activity,CacheActivity::class.java))
                } else {
                    //登录
                    var intent = Intent(activity, LoginActivity::class.java)
                    startActivity(intent)
                }
            })

        }
    }

    override fun getPresenter(): P_UpData<IView_Main>? {
        return null
    }

    override fun getLayout(): Int {
        return R.layout.fragment_wd
    }
}