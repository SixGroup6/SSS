package com.bwie.sss.inter

import com.bwie.sss.bean.*
import io.reactivex.Flowable
import io.reactivex.Observable
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
    @GET("v2/feed?udid=26868b32e808498db32fd51fb422d00175e179df&vc=83")
    fun getVideo():Flowable<HomeBean>
    /*
        最后一条//date=1499043600000&num=2
     */
    @GET("v2/feed")
    fun getVideoMore(@Query("date") date :String,@Query("num") num :String):Flowable<HomeBean>

    @GET("reg")
    fun getRegister(@Query("mobile")moblie:String, @Query("password")password:String):Flowable<RegisterBean.RegisterBean>

    @GET("login")
    fun getLogin(@Query("mobile")moblie:String,@Query("password")password: String):Flowable<LoginBean.LoginBean>

    /**
     * 获取更多数据
     * http://baobab.wandoujia.com/api/v2/categories?udid=26868b32e808498db32fd51fb422d00175e179df&vc=83
     */
    @GET("v2/categories?udid=26868b32e808498db32fd51fb422d00175e179df&vc=83")
    fun getFindMoreData():Flowable<List<FindBean.Find>>

    /**
     * http://baobab.wandoujia.com/api/v3/videos?categoryName=%s&strategy=%s&udid=26868b32e808498db32fd51fb422d00175e179df&vc=83
     */
    @GET("v3/videos")
    fun getFindDetails(@Query("categoryName") categoryName : String ,@Query("strategy") strategy : String):Flowable<FindDetail.Detail>
    /**
     * 热门的排行
     * v3/ranklist?num=10&strategy=%s&udid=26868b32e808498db32fd51fb422d00175e179df&vc=83
     */
    @GET("v3/ranklist")
    fun getHotData(@Query("num") num :Int,@Query("strategy") strategy :String,
                   @Query("udid") udid :String,@Query("vc") vc :Int) : Observable<HotBean>


}