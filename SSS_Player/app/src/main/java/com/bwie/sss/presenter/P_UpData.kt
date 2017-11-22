package com.bwie.sss.presenter

import android.content.Context
import com.bwie.sss.bean.UpDataBean
import com.bwie.sss.model.IModel_UpData
import com.bwie.sss.model.Model_UpData
import com.bwie.sss.view.IView_Main
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * 1:类的用途
 * 2：@author Dell
 * 3：@date 2017/11/22
 */
class P_UpData : BasePresenter<IView_Main>() {
    var model : IModel_UpData = Model_UpData()

    fun getUpData(context: Context){
        val versionCode = context.packageManager.getPackageInfo(context.packageName, 0).versionCode
        model.getUpData(context)
                ?.subscribeOn(Schedulers.io())
                ?.observeOn(AndroidSchedulers.mainThread())
                ?.subscribe { upData : UpDataBean.UpData ->
                    if (versionCode < upData.versionName.toInt()){
                        view?.setUpdata(upData)
                    }
                }
    }

    fun getProgressBar(context: Context){

    }
}