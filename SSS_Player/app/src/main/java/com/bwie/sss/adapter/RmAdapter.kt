package com.bwie.sss.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter

/**
 *  TabLayout +ViewPager  做适配
 */
class RmAdapter(manager: FragmentManager ,list: ArrayList<Fragment>,TabTitle : ArrayList<String> ): FragmentPagerAdapter(manager) {

    val  mMan :FragmentManager =manager!!
    val list :ArrayList<Fragment> = list;
    val  tabtitle: ArrayList<String> = TabTitle

    override fun getItem(position: Int): Fragment {
        return  list.get(position)
    }

    override fun getCount(): Int {

        return list.size
    }

    override fun getPageTitle(position: Int): CharSequence {
        return  tabtitle[position]
    }
}