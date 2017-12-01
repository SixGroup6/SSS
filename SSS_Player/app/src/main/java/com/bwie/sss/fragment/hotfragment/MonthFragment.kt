package com.bwie.sss.fragment.hotfragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bwie.sss.R
import com.bwie.sss.adapter.MonthAdapter
import com.bwie.sss.bean.HotBean
import com.bwie.sss.presenter.HotPresenter
import com.bwie.sss.view.IHotView

/**
 * Created by Dell on 2017/11/29.
 */
class MonthFragment  :Fragment(),IHotView.view{

    lateinit var mpresent : HotPresenter
    var recyclerView :RecyclerView?= null
    var list : ArrayList<HotBean.ItemListBean.DataBean> = java.util.ArrayList()
    lateinit var mAdapter :MonthAdapter
    lateinit var strategy :String

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater!!.inflate(R.layout.fragment_moth, null)
        return  view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        var  recyclerView = view?.findViewById(R.id.recyclerView) as RecyclerView

        recyclerView!!.layoutManager= LinearLayoutManager(context)
        mAdapter = MonthAdapter(context, list)
        recyclerView!!.adapter= mAdapter
        if(arguments != null){
            strategy= arguments.getString("strategy")
            mpresent= HotPresenter(context,this)
            mpresent.requestData(strategy)
        }

    }
    override fun setBean(hotBean: HotBean) {
            if(list.size >0 ){
                list.clear()
            }
        hotBean.itemList?.forEach {
            it.data?.let {  i -> list.add(i) }
        }
        mAdapter.notifyDataSetChanged()
    }

    override fun requestData(strategy: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun start() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


}