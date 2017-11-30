package com.bwie.sss.fragment

import android.content.Intent
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import com.bwie.sss.R
import com.bwie.sss.activity.FindDetailActivity
import com.bwie.sss.adapter.FindMoreAdapter
import com.bwie.sss.bean.FindBean
import com.bwie.sss.presenter.FindPresenter
import com.bwie.sss.view.IView_Find
import kotlinx.android.synthetic.main.fragment_fx.*

/**
 * 1:类的用途
 * 2：@author Dell
 * 3：@date 2017/11/27
 */
class Fragment_fx : BaseFragment<IView_Find,FindPresenter>(),IView_Find {

    /**
     * 初始化数据
     */
    override fun initData() {
        //设置布局管理器
        fx_rv.layoutManager = GridLayoutManager(context,2) as RecyclerView.LayoutManager?
        //调p层方法请求网络 获取数据
        presenter!!.getFindData(context)
    }

    override fun getPresenter(): FindPresenter? {
        return FindPresenter()
    }

    override fun getLayout(): Int {
        return R.layout.fragment_fx
    }

    override fun getFindData(finds: List<FindBean.Find>) {
        val adapter = FindMoreAdapter(context, finds)
        fx_rv.adapter = adapter
        adapter.setClicks(object : FindMoreAdapter.RecycleViewItemClick{
            override fun itemClick(position: Int) {
                val intent = Intent(context, FindDetailActivity::class.java)
                intent.putExtra("name",finds.get(position).name)
                context.startActivity(intent)
            }
        })
    }
}