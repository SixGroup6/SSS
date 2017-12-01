package com.bwie.sss.view

import com.bwie.sss.bean.HotBean

/**
 * Created by Dell on 2017/11/30.
 */
interface  IHotView {

    interface  view : Presenter{
        fun setBean(hotBean: HotBean)
    }

    interface Presenter : BasePresenter {
        fun requestData(strategy: String)
    }

    interface BasePresenter{
        fun start()
    }

}