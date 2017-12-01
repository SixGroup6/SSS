package com.bwie.sss.model

import android.content.Context
import com.bwie.sss.bean.FindDetail
import io.reactivex.Flowable

/**
 * 1:类的用途
 * 2：@author Dell
 * 3：@date 2017/11/30
 */
interface IModel_FindDetail {
    fun getDetailData(context: Context, categoryName : String, strategy : String) : Flowable<FindDetail.Detail>
    fun getMoreData(context: Context, start : Int, num : Int,categoryName: String, data: String): Flowable<FindDetail.Detail>
}