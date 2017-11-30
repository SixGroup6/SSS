package com.bwie.sss.view

import com.bwie.sss.bean.FindBean

/**
 * 1:类的用途
 * 2：@author Dell
 * 3：@date 2017/11/29
 */
interface IView_Find {
    fun getFindData(finds : List<FindBean.Find>)
}