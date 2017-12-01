package com.bwie.sss.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bwie.sss.R
import com.bwie.sss.bean.HotBean
import com.squareup.picasso.Picasso

/**
 * Created by Dell on 2017/11/30.
 */
class TotalAdapter (context:Context ,list:ArrayList<HotBean.ItemListBean.DataBean>):RecyclerView.Adapter<TotalAdapter.ViewHolder>() {

    var context: Context? = null;
    var list : ArrayList<HotBean.ItemListBean.DataBean>? = null
    var layoutin : LayoutInflater? = null
    init {
        this.context = context
        this.list =list
        this.layoutin = LayoutInflater.from(context)
    }


    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        //加载图片的
        var photoURl = list?.get(position)?.cover?.feed
        Picasso.with(context).load(photoURl).into(holder?.iv_photo)
        //标题
        var title = list?.get(position)?.title
        holder?.tv_title?.text = title
        //时间
        var duration = list?.get(position)?.duration
        var category = list?.get(position)?.category
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
        return ViewHolder(layoutin?.inflate(R.layout.item_total,parent,false),context!!)
    }

    override fun getItemCount(): Int {
        return list?.size?:0
    }


    class ViewHolder(itemView: View?, context: Context) : RecyclerView.ViewHolder(itemView) {
        var iv_photo: ImageView? = itemView?.findViewById(R.id.iv_photo) as ImageView
        var tv_title: TextView? = itemView?.findViewById(R.id.tv_title) as TextView
        var tv_time: TextView? = itemView?.findViewById(R.id.tv_time) as TextView
        init {
//            tv_title?.typeface = Typeface.createFromAsset(context?.assets, "fonts/FZLanTingHeiS-L-GB-Regular.TTF")

        }
    }
}