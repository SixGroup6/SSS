package com.bwie.sss.fragment

import android.content.Intent
import android.view.View
import com.bwie.sss.R
import com.bwie.sss.activity.CacheActivity
import com.bwie.sss.presenter.P_UpData
import com.bwie.sss.view.IView_Main
import kotlinx.android.synthetic.main.fragment_wd.*

/**
 * 1:类的用途
 * 2：@author Dell
 * 3：@date 2017/11/27
 */
class Fragment_wd : BaseFragment<IView_Main,P_UpData<IView_Main>>(){
    override fun initData() {
        tv_save.setOnClickListener{
            startActivity(Intent(activity,CacheActivity::class.java))
        }
    }

    override fun getPresenter(): P_UpData<IView_Main>? {
        return null
    }

    override fun getLayout(): Int {
        return R.layout.fragment_wd
    }
}