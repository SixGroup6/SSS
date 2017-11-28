package com.bwie.sss.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bwie.sss.presenter.BasePresenter

/**
 * 1:类的用途
 * 2：@author Dell
 * 3：@date 2017/11/27
 */
abstract class BaseFragment<V,T : BasePresenter<V>> : Fragment() {
    var presenter : T? = null
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater!!.inflate(getLayout(), container, false)
        presenter = getPresenter()
        if (presenter != null) {
            presenter!!.attachView(this as V)
        }
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initData()//初始化的方法
    }

    abstract fun initData()

    internal abstract fun getPresenter(): T

    abstract fun getLayout(): Int

    override fun onDestroy() {
        super.onDestroy()
        if (presenter != null){
            presenter!!.deattachView()
        }
    }
}