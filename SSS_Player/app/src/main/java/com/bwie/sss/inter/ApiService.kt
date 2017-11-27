package com.bwie.sss.inter

import com.bwie.sss.bean.LoginBean
import com.bwie.sss.bean.RegisterBean
import com.bwie.sss.bean.UpDataBean
import com.bwie.sss.bean.VideoBean
import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * 1:类的用途
 * 2：@author Dell
 * 3：@date 2017/11/22
 */
interface ApiService {
    /*
       请求后台，版本更新
     */
    @GET("08web/UpDataServlet")
    fun getUpDtad():Flowable<UpDataBean.UpData>

    /*
     *获取Video页数据
     */
    @GET("v2/feed?num=2&udid=26868b32e808498db32fd51fb422d00175e179df&vc=83")
    fun getVideo():Flowable<VideoBean.Video>
    /*
        最后一条//date=1499043600000&num=2
     */
    @GET("v2/feed")
    fun getVideoEnd(@Query("date")date:String,@Query("num")num:String):Flowable<VideoBean.Video>

    @GET("reg")
    fun getRegister(@Query("mobile")moblie:String, @Query("password")password:String):Flowable<RegisterBean.RegisterBean>

    @GET("login")
    fun getLogin(@Query("mobile")moblie:String,@Query("password")password: String):Flowable<LoginBean.LoginBean>
}