package com.bwie.sss.model

import android.content.Context
import com.bwie.sss.api.Api
import com.bwie.sss.bean.FindDetail
import com.bwie.sss.inter.ApiService
import com.tt.lvruheng.eyepetizer.network.RetrofitClient
import io.reactivex.Flowable

/**
 * 1:类的用途
 * 2：@author Dell
 * 3：@date 2017/11/30
 */
class Model_Detail : IModel_FindDetail {
    override fun getDetailData(context: Context,categoryName: String, strategy: String): Flowable<FindDetail.Detail> {
        val apiService = RetrofitClient.getInstance(context).create(ApiService::class.java, Api.VIDEO)
        return apiService!!.getFindDetails(categoryName,strategy)
    }

}