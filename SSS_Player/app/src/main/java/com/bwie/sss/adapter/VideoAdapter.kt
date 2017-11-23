package com.bwie.sss.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.bwie.sss.bean.VideoBean

/**
 * Created by 燕子 on 2017/11/23.
 */
class VideoAdapter(context:Context,list:VideoBean.Issue): RecyclerView.Adapter<VideoAdapter.ViewHoler>() {
    override fun getItemCount(): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHoler {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onBindViewHolder(holder: ViewHoler?, position: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    class ViewHoler: RecyclerView.ViewHolder(item:View) {

    }


}