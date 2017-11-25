package com.bwie.sss.presenter

import android.content.Context

/**
 * Created by 苏康泰 on 2017/11/22.
 */
interface LoginPresenterInterface {
    fun login(mobile:String,password:String,context: Context)
}