package com.bwie.sss.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bwie.sss.R
import com.bwie.sss.bean.FindDetail
import com.squareup.picasso.Picasso

/**
 * 1:类的用途
 * 2：@author Dell
 * 3：@date 2017/11/30
 */
class FindDetailAdapter(var context: Context, var dlist: List<FindDetail.Item>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {
        holder as MyViewHolder
        holder.pic.scaleType = ImageView.ScaleType.FIT_XY
        holder.titles.setText(dlist.get(position).data.title)
        Picasso.with(context).load(dlist.get(position).data.cover?.feed).into(holder.pic)
        holder.pic.setOnClickListener({
            click!!.click(position)
        })
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent!!.context).inflate(R.layout.find_detail_item, parent, false)
        val myViewHolder = MyViewHolder(view)
        return myViewHolder
    }

    override fun getItemCount(): Int {
        return dlist.size
    }

    class MyViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {
        var pic: ImageView = itemView!!.findViewById(R.id.fd_pic) as ImageView
        var titles: TextView = itemView!!.findViewById(R.id.fd_title) as TextView
    }

    var click: RecycleItemClick? = null

    interface RecycleItemClick {
        fun click(position: Int)
    }

    fun setClicks(click: RecycleItemClick) {
        this.click = click
    }
}