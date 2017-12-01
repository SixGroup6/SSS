package com.bwie.sss.adapter

import android.content.Context
import android.content.Intent
import android.os.Parcelable
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bwie.sss.R
import com.bwie.sss.activity.VideoActivity_rm
import com.bwie.sss.bean.HotBean
import com.bwie.sss.bean.details
import com.squareup.picasso.Picasso

/**
 * Created by Dell on 2017/11/30.
 */
class WeekAdapter(context:Context ,list:ArrayList<HotBean.ItemListBean.DataBean> ) : RecyclerView.Adapter<WeekAdapter.ViewHolder>() {
    var context: Context? = null;
    var list : ArrayList<HotBean.ItemListBean.DataBean>? = null
    var layoutin :LayoutInflater? = null
    init {
        this.context = context
        this.list =list
        this.layoutin = LayoutInflater.from(context)
    }

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        //加载图片的
        holder?.iv_photo?.scaleType=ImageView.ScaleType.FIT_XY
        var photoURl = list?.get(position)?.cover?.feed
        holder?.iv_photo?.scaleType = ImageView.ScaleType.FIT_XY
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
        holder?.itemView?.setOnClickListener{
            var  intent  :Intent = Intent(context , VideoActivity_rm::class.java)
            var desc = list?.get(position)?.description     //介绍
            var playUrl = list?.get(position)?.playUrl      //播放
            var blurred = list?.get(position)?.cover?.blurred       //布局下面的图片
            var collect = list?.get(position)?.consumption?.collectionCount     //上传
            var share = list?.get(position)?.consumption?.shareCount        //分享
            var reply = list?.get(position)?.consumption?.replyCount        //  回答
            var time = System.currentTimeMillis()
            var  details= details(photoURl,title,desc,duration,playUrl,category,blurred,collect ,share ,reply,time)

            intent.putExtra("UR1",playUrl.toString())
            intent.putExtra("data",details as Parcelable)

            context?.let { context -> context.startActivity(intent) }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        return ViewHolder(layoutin?.inflate(R.layout.item_week, parent, false), context!!)
    }

    override fun getItemCount(): Int {
        return  list?.size?:0

    }


    class ViewHolder(itemView: View?, context: Context) : RecyclerView.ViewHolder(itemView) {
        var iv_photo: ImageView? = itemView?.findViewById(R.id.iv_photo) as ImageView
        var tv_title: TextView? = itemView?.findViewById(R.id.tv_title) as TextView
        var tv_time: TextView? = itemView?.findViewById(R.id.tv_time) as TextView

    }

}