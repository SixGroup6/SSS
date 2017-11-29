package com.bwie.sss.model

import android.content.Context
import android.util.Log
import com.bwie.sss.api.Api
import com.bwie.sss.bean.HomeBean
import com.bwie.sss.bean.UpDataBean
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
    override fun getVideo(context: Context,isFirst: Boolean,data: String?): Flowable<HomeBean>? {
        val retrofitClient= RetrofitClient.getInstance(context)
        val apiService= retrofitClient.create(ApiService::class.java, Api.VIDEO)
        when(isFirst) {
            true -> return apiService?.getVideo()

            false -> return apiService?.getVideoMore(data.toString(),"2")
        }

    }
}