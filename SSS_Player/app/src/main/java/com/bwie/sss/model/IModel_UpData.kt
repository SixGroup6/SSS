package com.bwie.sss.model

import android.content.Context
import com.bwie.sss.bean.UpDataBean
import com.bwie.sss.bean.VideoBean
import io.reactivex.Flowable

/**
 * 1:类的用途
 * 2：@author Dell
 * 3：@date 2017/11/22
 */
interface IModel_UpData {

    fun getUpData(context: Context) : Flowable<UpDataBean.UpData>?

    fun getloadVideo(context: Context, isB: Boolean,date:String):Flowable<VideoBean.Video>?

}