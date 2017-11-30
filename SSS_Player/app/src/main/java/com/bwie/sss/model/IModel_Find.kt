package com.bwie.sss.model

import android.content.Context
import com.bwie.sss.bean.FindBean
import io.reactivex.Flowable

/**
 * 1:类的用途
 * 2：@author Dell
 * 3：@date 2017/11/29
 */
interface IModel_Find {
    fun getFindData(context: Context) : Flowable<List<FindBean.Find>>
}