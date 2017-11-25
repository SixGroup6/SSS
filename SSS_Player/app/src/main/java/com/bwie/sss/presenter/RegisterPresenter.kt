package com.bwie.sss.presenter

import android.content.Context
import android.util.Log
import com.bwie.sss.callback.OnRegisterFinishListener
import com.bwie.sss.model.RegisterModel
import com.bwie.sss.model.RegisterModelInterface
import com.bwie.sss.view.RegisterViewInterface


/**
 * Created by 苏康泰 on 2017/11/22.
 */
class RegisterPresenter(rgViewInterface: RegisterViewInterface): BasePresenter<RegisterViewInterface>(),RegisterPresenterInterface,OnRegisterFinishListener{

    lateinit var registerModel:RegisterModelInterface
    lateinit var registerViewInterface:RegisterViewInterface
    init {
        registerViewInterface=rgViewInterface
        registerModel=RegisterModel()
    }

    override fun register(username: String, password: String,context: Context) {

        Log.e("xxx2",username+password)
        registerModel.RegisterUser(username,password,context,this)

    }
    override fun onsetUsername() {

        registerViewInterface.setRegisterUserError()
    }

    override fun onsetPassword() {

        registerViewInterface.setRegisterPwdError()
    }

    override fun Success() {

        registerViewInterface.onSuccess()
    }

    override fun Wrong() {

        registerViewInterface.onWrong()
    }

}