package com.bwie.sss.presenter

import android.content.Context
import com.bwie.sss.model.IModel_FindDetail
import com.bwie.sss.model.Model_Detail
import com.bwie.sss.view.IView_FindDetail

/**
 * 1:类的用途
 * 2：@author Dell
 * 3：@date 2017/11/30
 */
class FindDetialPresenter : BasePresenter<IView_FindDetail>() {
    var model : IModel_FindDetail = Model_Detail()

    fun getDetail(context: Context,categoryName: String, strategy: String){
        val flowable = model.getDetailData(context, categoryName, strategy)
    }
}