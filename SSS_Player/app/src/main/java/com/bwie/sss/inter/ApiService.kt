package com.bwie.sss.inter

import com.bwie.sss.bean.UpDataBean
import io.reactivex.Flowable
import retrofit2.http.GET

/**
 * 1:类的用途
 * 2：@author Dell
 * 3：@date 2017/11/22
 */
interface ApiService {
    /*
       请求后台，版本更新
     */
    @GET("UpDataServlet")
    fun getUpDtad():Flowable<UpDataBean.UpData>
}