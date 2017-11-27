package com.bwie.sss.presenter

import android.content.Context
import android.util.Log
import com.bwie.sss.bean.UpDataBean
import com.bwie.sss.bean.VideoBean
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
    var model: IModel_UpData = Model_UpData()


    fun getUpData(context: Context) {
        Log.i("xx", "VideoPresenterlmp")
        val flowable = model.getUpData(context)
        flowable?.subscribeOn(Schedulers.io())
                ?.observeOn(Schedulers.newThread())
                ?.subscribe { upData: UpDataBean.UpData ->
                    if (view == null) {
                        Log.i("xxx", upData.apkUrl)
                    }
                    view?.setUpdata(upData)
                }
    }

    fun getloadVideo(context: Context){
        var v=model?.getloadVideo(context,true,"0")
        v?.subscribeOn(Schedulers.io())?.observeOn(AndroidSchedulers.mainThread())?.subscribe { bean: VideoBean.Video ->
            Log.e("bean", bean.toString())
            view?.setVideo(bean)
        }
    }
    fun getloadVideoEnd(context: Context,date: String){
        Log.i("xx","VideoPresenterlmp")
        var v=model?.getloadVideo(context,false,date)
        v?.subscribeOn(Schedulers.io())?.observeOn(AndroidSchedulers.mainThread())?.subscribe { bean: VideoBean.Video ->
            Log.e("bean", bean.toString())
            view?.setVideo(bean)
        }
    }
}