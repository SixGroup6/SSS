package com.bwie.sss.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import com.bwie.sss.R
import com.bwie.sss.adapter.RmAdapter
import com.bwie.sss.fragment.hotfragment.WeekFragment
import com.bwie.sss.presenter.P_UpData
import com.bwie.sss.view.IView_Main
import kotlinx.android.synthetic.main.fragment_rm.*


/**
 * 1:类的用途
 * 2：@author Dell
 * 3：@date 2017/11/27
 */
class Fragment_rm :BaseFragment<IView_Main,P_UpData<IView_Main>>(){

    var Tabtitle = arrayListOf<String>("周排行", "月排行", "总排行")
    val STRATEGY = arrayOf("weekly", "monthly", "historical")
    var FragmentList = arrayListOf<Fragment>()

    override fun getPresenter(): P_UpData<IView_Main>? {
        return P_UpData()
    }

    override fun getLayout(): Int {
       return R.layout.fragment_rm
    }
    override fun initData() {

        var weekFragment = WeekFragment()
        var weekbundle= Bundle()
        weekbundle.putString("strategy", STRATEGY[0])
        weekFragment.arguments= weekbundle

        var mothFragment = WeekFragment()
        var mothbundle= Bundle()
        mothbundle.putString("strategy", STRATEGY[1])
        mothFragment.arguments= mothbundle

        val totalFragment = WeekFragment()
        var totalBundle = Bundle()
        totalBundle.putString("strategy" , STRATEGY[2])
        totalFragment.arguments = totalBundle

        FragmentList = ArrayList()
        FragmentList.add(weekFragment as Fragment)
        FragmentList.add(mothFragment as Fragment)
        FragmentList.add(totalFragment as Fragment)
        viewpager.adapter = RmAdapter(fragmentManager, FragmentList,Tabtitle)
        tab.setupWithViewPager(viewpager)

    }

}