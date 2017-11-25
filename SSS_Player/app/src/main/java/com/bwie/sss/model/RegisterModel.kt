package com.bwie.sss.model

import android.content.Context
import android.text.TextUtils
import android.util.Log
import android.widget.Toast
import com.bwie.sss.api.Api
import com.bwie.sss.bean.RegisterBean
import com.bwie.sss.callback.OnRegisterFinishListener
import com.bwie.sss.inter.ApiService
import com.bwie.sss.util.TelNumMatch
import com.tt.lvruheng.eyepetizer.network.RetrofitClient
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by 苏康泰 on 2017/11/22.
 */
class RegisterModel:RegisterModelInterface{

    override fun RegisterUser(username: String, password: String,context: Context,listener:OnRegisterFinishListener) {
        Log.e("xxx1", "" + username + password)
        if (TextUtils.isEmpty(username)) {
            listener.onsetUsername()
            return
        }
        if (TextUtils.isEmpty(password)) {
            listener.onsetPassword()
            return
        }
        if (!TextUtils.isEmpty(username) && !TextUtils.isEmpty(password)) {
            val validPhoneNumber = TelNumMatch.isValidPhoneNumber(username)
            if (validPhoneNumber) {
                var create = RetrofitClient.getInstance(context, Api.REGISTER_URL).create(ApiService::class.java)
                var flowable = create!!.getRegister(username, password)
                Log.e("xxx3", "" + username + password)
                flowable.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe { bean: RegisterBean.RegisterBean ->
                            if (bean.code.equals("0")) {
                                listener.Success()
                                Toast.makeText(context, "" + bean.msg, Toast.LENGTH_SHORT).show()
                            }
                            if (bean.code.equals("1")) {
                                listener.Wrong()
                                Toast.makeText(context, "" + bean.msg, Toast.LENGTH_SHORT).show()
                            }
                        }

            }else{
                Toast.makeText(context,"请输入正确的手机号",Toast.LENGTH_SHORT).show()
            }
        }
    }
}