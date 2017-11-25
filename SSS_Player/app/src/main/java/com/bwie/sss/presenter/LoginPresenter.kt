package com.bwie.sss.presenter

import android.content.Context
import com.bwie.sss.callback.OnLoginFinishListener
import com.bwie.sss.model.LoginModel
import com.bwie.sss.model.LoginModelInterface
import com.bwie.sss.view.LoginViewInterface

/**
 * Created by 苏康泰 on 2017/11/22.
 */
class LoginPresenter(lgViewInterface: LoginViewInterface) :BasePresenter<LoginViewInterface>(),LoginPresenterInterface,OnLoginFinishListener{


    lateinit var loginViewInterface:LoginViewInterface
    lateinit var loginmodel:LoginModelInterface
    init {
        loginViewInterface=lgViewInterface
        loginmodel=LoginModel()
    }
    override fun login(mobile: String, password: String, context: Context) {


        loginmodel.LoginUser(mobile,password,this,context)
    }
    override fun Success() {
        loginViewInterface.onSuccess()
    }

    override fun Error() {
        loginViewInterface.onError()
    }

    override fun LgsetUsername() {
        loginViewInterface.setLoginUserError()
    }

    override fun LgonsetPassword() {
        loginViewInterface.setLoginPwdError()
    }
}