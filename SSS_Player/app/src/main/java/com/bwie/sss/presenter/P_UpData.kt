package com.bwie.sss.presenter

import android.content.Context
import android.util.Log
import com.bwie.sss.api.Api
import com.bwie.sss.bean.UpDataBean
import com.bwie.sss.bean.VideoBean
import com.bwie.sss.model.IModel_UpData
import com.bwie.sss.model.Model_UpData
import com.bwie.sss.view.IView_Main
import io.reactivex.Flowable
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

       var f: Flowable<UpDataBean.UpData> = model.getUpData(context)!!
               f?.subscribeOn(Schedulers.io())
                ?.observeOn(Schedulers.newThread())
                ?.subscribe { upData : UpDataBean.UpData ->
                    view?.setUpdata(upData)
                }
    }


    fun getloadVideo(context: Context){
        Log.i("xx","VideoPresenterlmp")
        var v=model?.getloadVideo(context, Api.VIDEO,true)
        v?.subscribeOn(Schedulers.io())?.observeOn(AndroidSchedulers.mainThread())?.subscribe { bean: VideoBean.Video->
            Log.e("bean",bean.toString())
            view?.setVideo(bean)
           // view!!.setVideo(bean)
        }

    }


    fun getProgressBar(context: Context){

    }
}