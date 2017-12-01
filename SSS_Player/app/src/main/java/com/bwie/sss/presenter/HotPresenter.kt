package com.bwie.sss.presenter

import android.content.Context
import com.bwie.sss.bean.HotBean
import com.bwie.sss.model.HotModel
import com.bwie.sss.view.IHotView
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by Dell on 2017/11/30.
 */
class HotPresenter (context: Context , iHotView: IHotView.view) : IHotView.Presenter {
    override fun start() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    var mContext:Context? = null
    var mView : IHotView.view? = null
    val mModel : HotModel by lazy {
        HotModel()
    }

    init {
        mView = iHotView
        mContext = context
    }

    override fun requestData(strategy: String) {
        val observable : Observable<HotBean> = mModel.loadData(mContext!!, strategy).subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
        observable?.subscribe{bean :HotBean->
            mView?.setBean(bean)
        }
    }

}