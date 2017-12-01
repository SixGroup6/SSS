package com.bwie.sss.activity

import android.content.Context
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TabHost
import android.widget.TextView
import com.bwie.sss.R
import com.bwie.sss.fragment.Fragment_fx
import com.bwie.sss.fragment.Fragment_rm
import com.bwie.sss.fragment.Fragment_sy
import com.bwie.sss.fragment.Fragment_wd
import com.bwie.sss.presenter.P_UpData
import com.bwie.sss.view.IView_Main
import kotlinx.android.synthetic.main.activity_home.*


class HomeActivity : BaseActivity<IView_Main,P_UpData<Any?>>() {

    var fragments = arrayOf<Class<*>>(Fragment_sy::class.java,Fragment_fx::class.java,Fragment_rm::class.java,Fragment_wd::class.java)
    var mImageViewArray = intArrayOf(R.drawable.but_sy,R.drawable.but_fx,R.drawable.but_rm,R.drawable.but_wd)
    var mTextviewArray = arrayOf("首页","发现","热门","我的")

    companion object {
        var context: Context? = null
    }

    override fun getLayout(): Int {
        return R.layout.activity_home
    }

    override fun getPresenter(): P_UpData<Any?>? {
        return null
    }

    override fun initData() {
        context = this
        //得到fragment个数
        var count : Int = fragments.size
        tabhost.setup(this,supportFragmentManager,R.id.realtabcontent)
        //tabhost.setup(this,supportFragmentManager,R.id.realtabcontent)
        for (i in 0 until count) {
            //为每一个Tab按钮设置图标、文字和内容
            val tabSpec : TabHost.TabSpec = tabhost.newTabSpec(mTextviewArray[i]).setIndicator(getTabItemView(i))
            //将Tab按钮添加进Tab选项卡中
            tabhost.addTab(tabSpec, fragments[i], null)
            //设置Tab按钮的背景
            //mTabHost.getTabWidget().getChildAt(i).setBackgroundResource(R.drawable.i11);
        }

        //去掉分隔的竖线
        tabhost.getTabWidget().setShowDividers(LinearLayout.SHOW_DIVIDER_NONE)
    }

    private fun getTabItemView(i: Int): View? {
        var view : View = layoutInflater.inflate(R.layout.tab_item_view, null)
        val tab_iv : ImageView = view.findViewById(R.id.tab_iv) as ImageView
        val tab_tv : TextView = view.findViewById(R.id.tab_tv) as TextView
        tab_iv.setImageResource(mImageViewArray[i])
        tab_tv.setText(mTextviewArray[i])
        return view
    }


}
