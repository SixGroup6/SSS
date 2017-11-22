package com.bwie.sss.model

import android.content.Context
import com.bwie.sss.api.Api
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
        val apiService = RetrofitClient.getInstance(context, Api.UP_DATA).create(ApiService::class.java)
        return apiService?.getUpDtad()
    }
}