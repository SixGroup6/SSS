package com.bwie.sss.adapter

import android.content.Context
import android.icu.lang.UCharacter.GraphemeClusterBreak.L
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bwie.sss.R
import com.bwie.sss.bean.VideoBean
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer

/**
 * Created by 燕子 on 2017/11/23.
 */
class VideoAdapter(var context:Context,var video:VideoBean.Video): RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    val TYPE_A:Int=0
    val TYPE_B:Int=1

    override fun getItemCount(): Int {
        Log.i("x","getItemCount")
      return video.issueList[0].itemList.size
    }

    override fun getItemViewType(position: Int): Int {
        if (position==0){
            return TYPE_A
        }else{
            return TYPE_B
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder {
        if (viewType==TYPE_A) {
            val v2=LayoutInflater.from(context).inflate(R.layout.recycler_item,parent,false);
            var  myViewHolder2=ViewHolder2(v2)
            return myViewHolder2
        }else{
            val v=LayoutInflater.from(context).inflate(R.layout.recycler_video,parent,false);
            var myViewHoler=ViewHoler(v)
            return myViewHoler
        }

        Log.i("x","onCreateViewHolder")

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {
        val type = getItemViewType(position)
        if(type==0){
            holder as ViewHolder2
            //图片加载框架



        }else{
            holder as ViewHoler
            holder.title.text=video.issueList[0].itemList[position].data.title
            holder.videoplay.setUp(video.issueList[0].itemList[position].data.playUrl,video.issueList[0].itemList[position].data.title,true)
           Log.i("v", video.issueList[0].itemList[position].data.playUrl+"  ")

        }
    }
    class ViewHoler (itemView: View?): RecyclerView.ViewHolder(itemView) {
            var title:TextView= itemView!!.findViewById(R.id.vide_title) as TextView
            var download:ImageView= itemView!!.findViewById(R.id.vide_download) as ImageView
             var videoplay: JCVideoPlayer = itemView!!.findViewById(R.id.vide_video) as JCVideoPlayer
            var show: ImageView? = itemView!!.findViewById(R.id.vide_show) as ImageView?

    }
    class ViewHolder2 (itemView: View?):RecyclerView.ViewHolder(itemView){
        var Img:ImageView= itemView!!.findViewById(R.id.vide_im) as ImageView
    }


}