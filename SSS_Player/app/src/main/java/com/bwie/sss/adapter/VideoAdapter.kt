package com.bwie.sss.adapter

import android.content.Context
import android.content.Intent
import android.os.Parcelable
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bwie.sss.R
import com.bwie.sss.activity.DetailActivity
import com.bwie.sss.bean.HomeBean
import com.bwie.sss.bean.VideoBean
import com.bwie.sss.util.ObjectSaveUtils
import com.bwie.sss.util.SUtils
import com.squareup.picasso.Picasso


/**
 * Created by 燕子 on 2017/11/23.
 */
class VideoAdapter(var context:Context,var list:MutableList<HomeBean.IssueListBean.ItemListBean>?): RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    override fun getItemCount(): Int {
        Log.i("x","getItemCount")
      return list?.size ?:0
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder {

            val v=LayoutInflater.from(context).inflate(R.layout.recycler_video,parent,false);
            var myViewHoler=ViewHoler(v)
            return myViewHoler
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {
            holder as ViewHoler
            var bean = list?.get(position)
            holder.title.text=bean?.data?.title
            holder.iv_video.scaleType=ImageView.ScaleType.FIT_XY
            Picasso.with(context).load(bean?.data?.cover?.feed).into(holder.iv_video)
            holder.iv_video.setOnClickListener(View.OnClickListener {
                //跳转详情页
                var intent : Intent = Intent(context,DetailActivity::class.java)
                var desc = bean?.data?.description
                var duration = bean?.data?.duration
                var playUrl = bean?.data?.playUrl
                var blurred = bean?.data?.cover?.blurred
                var collect = bean?.data?.consumption?.collectionCount
                var share = bean?.data?.consumption?.shareCount
                var reply = bean?.data?.consumption?.replyCount
                var time = System.currentTimeMillis()
                var videoBean  = VideoBean(bean?.data?.cover?.feed,bean?.data?.title,desc,duration,playUrl,bean?.data?.category,blurred,collect ,share ,reply,time)
                var url = SUtils.getInstance(context!!,"beans").getString(playUrl!!)
                if(url.equals("")){
                    var count = SUtils.getInstance(context!!,"beans").getInt("count")
                    if(count!=-1){
                        count = count.inc()
                    }else{
                        count = 1
                    }
                    SUtils.getInstance(context!!,"beans").put("count",count)
                    SUtils.getInstance(context!!,"beans").put(playUrl!!,playUrl)
                    ObjectSaveUtils.saveObject(context!!,"bean$count",videoBean)
                }
                intent.putExtra("data",videoBean as Parcelable)
                context?.let { context -> context.startActivity(intent) }
            })


    }

    class ViewHoler (itemView: View?): RecyclerView.ViewHolder(itemView) {
            var title:TextView= itemView!!.findViewById(R.id.vide_title) as TextView
            var iv_video:ImageView=itemView!!.findViewById(R.id.iv_video) as ImageView

    }

}

