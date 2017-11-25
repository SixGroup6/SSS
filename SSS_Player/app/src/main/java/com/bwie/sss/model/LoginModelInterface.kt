package com.bwie.sss.model

import android.content.Context
import com.bwie.sss.callback.OnLoginFinishListener

/**
 * Created by 苏康泰 on 2017/11/22.
 */
interface LoginModelInterface {
    fun LoginUser(mobile:String,password:String,listener:OnLoginFinishListener,context:Context)
}