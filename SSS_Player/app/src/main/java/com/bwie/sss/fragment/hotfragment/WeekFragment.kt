package com.bwie.sss.fragment.hotfragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bwie.sss.R
import com.bwie.sss.adapter.WeekAdapter
import com.bwie.sss.bean.HotBean
import com.bwie.sss.presenter.HotPresenter
import com.bwie.sss.view.IHotView

/**
 * Created by Dell on 2017/11/29.
 */
class WeekFragment : Fragment() ,IHotView.view {
    override fun start() {

    }

    lateinit var mPresenter : HotPresenter
    var recycler:RecyclerView?= null
    lateinit var mStrategy: String
    lateinit var wAdapter : WeekAdapter
    var  list : ArrayList<HotBean.ItemListBean.DataBean> = ArrayList()

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater!!.inflate(R.layout.fragment_week, null)
        return  view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val recycler = view?.findViewById(R.id.recyclerView) as RecyclerView

        recycler.layoutManager =LinearLayoutManager(context)
        wAdapter = WeekAdapter(context, list)
        recycler.adapter = wAdapter
        if (arguments != null) {
            mStrategy = arguments.getString("strategy")
            mPresenter = HotPresenter(context, this)
            mPresenter.requestData(mStrategy)
        }

    }

    override fun setBean(hotBean: HotBean) {
        if(list.size >0){
            list.clear()
        }
        hotBean.itemList?.forEach {
            it.data?.let { i->  list.add(i) }
        }
        wAdapter.notifyDataSetChanged()
    }

    override fun requestData(strategy: String) {

    }

}