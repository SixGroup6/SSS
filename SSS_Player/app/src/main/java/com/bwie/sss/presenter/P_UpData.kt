package com.bwie.sss.presenter

import android.content.Context
import android.util.Log
import com.bwie.sss.bean.HomeBean
import com.bwie.sss.bean.UpDataBean
import com.bwie.sss.model.IModel_UpData
import com.bwie.sss.model.Model_UpData
import com.bwie.sss.view.IView_Main
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * 1:类的用途
 * 2：@author Dell
 * 3：@date 2017/11/22
 */
class P_UpData<T> : BasePresenter<IView_Main>() {
    var model: IModel_UpData = Model_UpData()

    fun getUpData(context: Context) {
        val flowable = model.getUpData(context)
        flowable?.subscribeOn(Schedulers.io())
                ?.observeOn(AndroidSchedulers.mainThread())
                ?.subscribe { upData: UpDataBean.UpData ->
                    if (view == null) {
                        Log.i("xxx", upData.apkUrl)
                    }
                    view?.setUpdata(upData)
                }
    }
    fun getFirstVideo(context: Context){
        var flowable_first=model.getVideo(context,true,"0")
        flowable_first?.subscribeOn(Schedulers.io())
                ?.observeOn(AndroidSchedulers.mainThread())
                ?.subscribe{ bean:HomeBean ->
                    view?.setVideo(bean)
                }

    }
    fun getMoreVideo(context: Context,data:String?){
        var v=model?.getVideo(context,false,data)
        v?.subscribeOn(Schedulers.io())?.observeOn(AndroidSchedulers.mainThread())?.subscribe { bean: HomeBean ->
            view?.setVideo(bean)
        }
    }
}