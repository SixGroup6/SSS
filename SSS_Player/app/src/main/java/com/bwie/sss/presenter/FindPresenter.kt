package com.bwie.sss.presenter

import android.content.Context
import android.util.Log
import com.bwie.sss.bean.FindBean
import com.bwie.sss.model.IModel_Find
import com.bwie.sss.model.Model_Find
import com.bwie.sss.view.IView_Find
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * 1:类的用途
 * 2：@author Dell
 * 3：@date 2017/11/29
 */
class FindPresenter : BasePresenter<IView_Find>(){
    var model : IModel_Find = Model_Find()

    fun getFindData(context: Context){
        val flowable = model.getFindData(context)
        flowable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { list : List<FindBean.Find> ->
                    view!!.getFindData(list)
                    Log.e("xxx",list.size.toString())
                }
    }
}