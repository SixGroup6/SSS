package com.bwie.sss.model

import android.content.Context
import com.bwie.sss.api.Api
import com.bwie.sss.bean.HotBean
import com.bwie.sss.inter.ApiService
import com.tt.lvruheng.eyepetizer.network.RetrofitClient
import io.reactivex.Observable

/**
 * Created by Dell on 2017/11/30.
 */
class HotModel  : HotModelInterface{

    override fun loadData(context: Context, strategy: String?): Observable<HotBean> {
        val api = RetrofitClient.getInstance(context).create(ApiService::class.java, Api.HOTDATE)
        return api!!.getHotData(10, strategy!!,"26868b32e808498db32fd51fb422d00175e179df",83)
    }
}