package com.bwie.sss.model

import android.content.Context
import com.bwie.sss.callback.OnRegisterFinishListener

/**
 * Created by 苏康泰 on 2017/11/22.
 */
interface RegisterModelInterface {
    fun RegisterUser(username: String, password: String,context: Context,listener:OnRegisterFinishListener)

}