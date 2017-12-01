package com.bwie.sss.presenter

import android.content.Context
import com.bwie.sss.bean.FindDetail
import com.bwie.sss.model.IModel_FindDetail
import com.bwie.sss.model.Model_Detail
import com.bwie.sss.view.IView_FindDetail
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * 1:类的用途
 * 2：@author Dell
 * 3：@date 2017/11/30
 */
class FindDetialPresenter<T> : BasePresenter<IView_FindDetail>() {
    var model : IModel_FindDetail = Model_Detail()

    fun getDetail(context: Context,categoryName: String, strategy: String){
        val flowable = model.getDetailData(context, categoryName, strategy)
        flowable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { detail:FindDetail.Detail ->
                    view!!.setDetail(detail)
                }
    }

    fun getMoreData(context: Context, start : Int, num : Int,categoryName: String, data: String){
        val flowable = model.getMoreData(context, start, num, categoryName, data)
        flowable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { detail : FindDetail.Detail->
                    view!!.setDetail(detail)
                }
    }
}