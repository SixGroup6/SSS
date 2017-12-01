package com.bwie.sss.model

import android.content.Context
import android.text.TextUtils
import android.util.Log
import android.widget.Toast
import com.bwie.sss.api.Api
import com.bwie.sss.bean.LoginBean
import com.bwie.sss.callback.OnLoginFinishListener
import com.bwie.sss.inter.ApiService
import com.tt.lvruheng.eyepetizer.network.RetrofitClient
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by 苏康泰 on 2017/11/22.
 */
class LoginModel :LoginModelInterface{
    override fun LoginUser(mobile: String, password: String, listener: OnLoginFinishListener, context: Context) {

        Log.e("xxx5",mobile+password)
        if(TextUtils.isEmpty(mobile)){
            listener.LgsetUsername()
            return
        }
        if(TextUtils.isEmpty(password)){
            listener.LgonsetPassword()
            return
        }

            var create = RetrofitClient.getInstance(context).create(ApiService::class.java, Api.REGISTER_URL)
            var flowable = create!!.getLogin(mobile, password)
            Log.e("xxx5",mobile+password)
            flowable.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe { bean: LoginBean.LoginBean ->
                        if(bean.code.equals("0")){
                            listener.Success()
                            Toast.makeText(context, "" + bean.msg, Toast.LENGTH_SHORT).show()
                        }
                        if(bean.code.equals("1")){
                            listener.Error()
                            Toast.makeText(context, "" + bean.msg, Toast.LENGTH_SHORT).show()
                        }
                    }

    }

}