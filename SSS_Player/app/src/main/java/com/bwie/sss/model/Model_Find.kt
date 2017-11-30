package com.bwie.sss.model

import android.content.Context
import com.bwie.sss.api.Api
import com.bwie.sss.bean.FindBean
import com.bwie.sss.inter.ApiService
import com.tt.lvruheng.eyepetizer.network.RetrofitClient
import io.reactivex.Flowable

/**
 * 1:类的用途
 * 2：@author Dell
 * 3：@date 2017/11/29
 */
class Model_Find :IModel_Find{
    override fun getFindData(context: Context): Flowable<List<FindBean.Find>> {
        val apiService = RetrofitClient.getInstance(context).create(ApiService::class.java, Api.VIDEO)
        return apiService!!.getFindMoreData()
    }
}