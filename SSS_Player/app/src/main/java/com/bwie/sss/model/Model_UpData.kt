package com.bwie.sss.model

import android.content.Context
import android.util.Log
import com.bwie.sss.api.Api
import com.bwie.sss.bean.UpDataBean
import com.bwie.sss.bean.VideoBean
import com.bwie.sss.inter.ApiService
import com.tt.lvruheng.eyepetizer.network.RetrofitClient
import io.reactivex.Flowable

/**
 * 1:类的用途
 * 2：@author Dell
 * 3：@date 2017/11/22
 */
class Model_UpData : IModel_UpData {
    override fun getUpData(context: Context): Flowable<UpDataBean.UpData>? {

       val apiService = RetrofitClient.getInstance(context).create(ApiService::class.java,Api.UP_DATA)
        return apiService?.getUpDtad()
    }
    override fun getloadVideo(context: Context, isB: Boolean,date:String): Flowable<VideoBean.Video>? {
        Log.i("date",date.toString())
        Log.i("xx","VideoModel")
        val retrofitClient= RetrofitClient.getInstance(context)
        Log.i("xx",Api.VIDEO)
        val apiService= retrofitClient.create(ApiService::class.java, Api.VIDEO)
        when(isB){
            true ->return apiService?.getVideo()!!//默认

            //apiService?.getVideoEnd(date.toString(),"2")!!
            false-> return apiService?.getVideoEnd(date.toString(),"2")!!
        }

    }
}