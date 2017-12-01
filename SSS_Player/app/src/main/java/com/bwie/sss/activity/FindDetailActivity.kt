package com.bwie.sss.activity

import android.content.Intent
import android.os.Parcelable
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import com.bwie.sss.R
import com.bwie.sss.adapter.FindDetailAdapter
import com.bwie.sss.bean.FindDetail
import com.bwie.sss.bean.VideoBean
import com.bwie.sss.presenter.FindDetialPresenter
import com.bwie.sss.view.IView_FindDetail
import kotlinx.android.synthetic.main.activity_find_detail.*
import java.util.*
import java.util.regex.Pattern

class FindDetailActivity : BaseActivity<IView_FindDetail, FindDetialPresenter<IView_FindDetail>>() ,IView_FindDetail,SwipeRefreshLayout.OnRefreshListener{

    var mIsRefresh: Boolean = false
    var data : String = ""
    var name : String = ""
    var mList = ArrayList<FindDetail.Item>()
    var mstart: Int = 10
    var adapter : FindDetailAdapter? = null

    override fun setDetail(detail: FindDetail.Detail) {
        val regEx = "[^0-9]"
        val p = Pattern.compile(regEx)
        val m = p.matcher(detail?.nextPageUrl)
        data = m.replaceAll("").subSequence(1, m.replaceAll("").length - 1).toString()
        if (mIsRefresh) {
            mIsRefresh = false
            find_srl.isRefreshing = false
            if (mList.size > 0) {
                mList.clear()
            }
        }
        detail.itemList?.forEach {
            it?.let { it -> mList.add(it) }
        }
        if (adapter == null){
            adapter = FindDetailAdapter(this,mList)
            find_rv.adapter = adapter
        }else{
            adapter!!.notifyDataSetChanged()
        }
        Log.e("list",mList.size.toString())
        adapter!!.setClicks(object : FindDetailAdapter.RecycleItemClick{
            override fun click(position: Int) {
                val intent = Intent(this@FindDetailActivity, DetailActivity::class.java)
                intent.putExtra("data",VideoBean(mList.get(position).data.cover.feed,mList.get(position).data.title,mList.get(position).data.description,mList.get(position).data.duration.toLong(),mList.get(position).data.playUrl,mList.get(position).data.category,mList.get(position).data.cover.blurred,0,0,0,0) as Parcelable)
                startActivity(intent)
            }
        })
    }

    override fun getLayout(): Int {
        return R.layout.activity_find_detail
    }

    override fun getPresenter(): FindDetialPresenter<IView_FindDetail>? {
        return FindDetialPresenter()
    }

    override fun initData() {
        name = intent.getStringExtra("name")
        detail_title.setText(name)
        find_rv.layoutManager = LinearLayoutManager(this)
        presenter!!.getDetail(this,name,"date")
        find_srl.setOnRefreshListener(this)
        //加载更多
        find_rv.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView?, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                var layoutManager: LinearLayoutManager = recyclerView?.layoutManager as LinearLayoutManager
                var lastPositon = layoutManager.findLastVisibleItemPosition()
                if (newState == RecyclerView.SCROLL_STATE_IDLE && lastPositon == mList.size-1) {
                    if (data != null) {
                        presenter?.getMoreData(this@FindDetailActivity,mstart,10,name,"date")
                        mstart += 10
                    }
                }
            }
        })
    }

    override fun onRefresh() {
        if (!mIsRefresh) {
            mIsRefresh = true
            presenter!!.getMoreData(this,mstart,10,name,"date")
        }
    }
}
