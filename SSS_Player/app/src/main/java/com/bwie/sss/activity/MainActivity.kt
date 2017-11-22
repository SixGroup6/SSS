package com.bwie.sss.activity

import com.bwie.sss.R
import com.bwie.sss.presenter.P_UpData
import com.bwie.sss.view.IView_Main

class MainActivity : BaseActivity<IView_Main,P_UpData>(),IView_Main {

    override fun getLayout(): Int {
        return R.layout.activity_main
    }

    override fun getPresenter(): P_UpData {
        return P_UpData()
    }

    override fun initData() {
        presenter?.getUpData(applicationContext)
    }

    override fun setUpdata() {

    }
}
