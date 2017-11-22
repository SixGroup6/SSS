package com.bwie.sss.presenter

import java.lang.ref.WeakReference

/**
 * 1:类的用途
 * 2：@author Dell
 * 3：@date 2017/11/22
 */

abstract class BasePresenter<V> {

    var weakReference: WeakReference<V>? = null
    //得到View
    val view: V?
        get() = if (weakReference != null) weakReference!!.get() else null

    //关联的方法
    fun attachView(view: V) {
        weakReference = WeakReference(view)
    }

    //接触关联的方法
    fun deattachView() {
        if (weakReference != null) {
            weakReference!!.clear()
            weakReference = null
        }
    }
}
