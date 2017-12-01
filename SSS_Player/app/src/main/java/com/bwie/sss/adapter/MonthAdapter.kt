package com.bwie.sss.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bwie.sss.R
import com.bwie.sss.activity.HomeActivity.Companion.context
import com.bwie.sss.bean.HotBean
import com.squareup.picasso.Picasso

/**
 * Created by Dell on 2017/11/30.
 */
class MonthAdapter (context:Context ,list:ArrayList<HotBean.ItemListBean.DataBean> ) : RecyclerView.Adapter<MonthAdapter.ViewHolder>() {
    var mcontext :Context ?= null
    var mlist : ArrayList<HotBean.ItemListBean.DataBean>?=null
    var layoutin :LayoutInflater?=null
    init {
        mcontext = context
        mlist =list
        layoutin = LayoutInflater.from(context)
    }

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        //加载图片的
        var photoURl = mlist?.get(position)?.cover?.feed
        Picasso.with(context).load(photoURl).into(holder?.iv_photo)
        //标题
        var title = mlist?.get(position)?.title
        holder?.tv_title?.text = title
        //时间
        var duration = mlist?.get(position)?.duration
        var category = mlist?.get(position)?.category
        var minute = duration?.div(60)
        val second = duration?.minus((minute?.times(60)) as Long)
        var realMinute : String
        var realSecond : String
        if(minute!!<10){
            realMinute = "0"+minute
        }else{
            realMinute = minute.toString()
        }
        if(second!!<10){
            realSecond = "0"+second
        }else{
            realSecond = second.toString()
        }
        holder?.tv_time?.text="$category/ $realMinute '$realSecond''"
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        val inflate = layoutin?.inflate(R.layout.item_moth, parent, false)
        return ViewHolder(inflate,mcontext!!)

    }

    override fun getItemCount(): Int {
        return  mlist?.size?:0
    }


    class ViewHolder(itemView: View? , context: Context) :RecyclerView.ViewHolder(itemView){
        var iv_photo: ImageView? = itemView?.findViewById(R.id.iv_photo) as ImageView
        var tv_title: TextView? = itemView?.findViewById(R.id.tv_title) as TextView
        var tv_time: TextView? = itemView?.findViewById(R.id.tv_time) as TextView
    }
}