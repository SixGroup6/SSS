package com.bwie.sss.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bwie.sss.R
import com.bwie.sss.bean.FindBean
import com.squareup.picasso.Picasso

/**
 * 1:类的用途
 * 2：@author Dell
 * 3：@date 2017/11/29
 */
class FindMoreAdapter(var context : Context,var flist : List<FindBean.Find>) :RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {
        holder as MyViewHolder
        Picasso.with(context).load(flist.get(position).bgPicture).into(holder.pic)
        holder.titles.setText(flist.get(position).name)
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent!!.context).inflate(R.layout.findmore_item, parent, false)
        val myViewHolder = MyViewHolder(view)
        view.setOnClickListener {
            click!!.itemClick(myViewHolder.position)
        }
        return myViewHolder
    }

    override fun getItemCount(): Int {
        return flist.size
    }

    class MyViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {
        var pic : ImageView = itemView!!.findViewById(R.id.fx_pic) as ImageView
        var titles : TextView = itemView!!.findViewById(R.id.fx_title) as TextView
    }
    var click : RecycleViewItemClick? = null
    interface RecycleViewItemClick{
        fun itemClick(position : Int)
    }

    fun setClicks(click: RecycleViewItemClick){
        this.click = click
    }
}