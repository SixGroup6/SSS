package com.bwie.sss.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.bwie.sss.R
import com.bwie.sss.presenter.BasePresenter
import com.jaeger.library.StatusBarUtil

/**
 * 1:类的用途
 * 2：@author Dell
 * 3：@date 2017/11/22
 */

abstract class BaseActivity<V, T : BasePresenter<V>> : AppCompatActivity() {

    var presenter: T? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.AppTheme)
        setContentView(getLayout())
        StatusBarUtil.setTranslucent(this,0)
        presenter = getPresenter()
        if (presenter != null) {
            presenter!!.attachView(this as V)
        }
        initData()//初始化的方法
    }

     abstract fun getLayout(): Int

     internal abstract fun getPresenter(): T?

     abstract fun initData()

    override fun onDestroy() {
        super.onDestroy()
        if (presenter != null) {
            presenter!!.deattachView()
        }
    }
}
