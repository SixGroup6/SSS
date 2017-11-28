package com.bwie.sss.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import android.widget.*

import com.bwie.sss.R
import com.bwie.sss.bean.FileIn
import com.bwie.sss.service.DownloadService

class FileAdapter(mContext: Context, mFilelist: List<FileIn>) : BaseAdapter() {

    private var mContext: Context? = null
    private var mFilelist: List<FileIn>? = null

    private val layoutInflater: LayoutInflater


    init {
        this.mContext = mContext
        this.mFilelist = mFilelist
        layoutInflater = LayoutInflater.from(mContext)
    }

    override fun getCount(): Int {
        return mFilelist!!.size
    }

    override fun getItem(position: Int): Any {
        return mFilelist!![position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var convertView = convertView
        var viewHolder: ViewHolder? = null
        val mFileInfo = mFilelist!![position]
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.cache_item, null)
            viewHolder = ViewHolder()
            viewHolder.textview = convertView!!.findViewById(R.id.file_textview) as TextView
            viewHolder.startButton = convertView.findViewById(R.id.start_button) as Button
            viewHolder.stopButton = convertView.findViewById(R.id.stop_button) as Button
            viewHolder.progressBar = convertView.findViewById(R.id.progressBar2) as ProgressBar


            viewHolder.textview!!.text = mFileInfo.fileName
            viewHolder.progressBar!!.max = 100

            viewHolder.startButton!!.setOnClickListener {
                val intent = Intent(mContext, DownloadService::class.java)
                intent.action = DownloadService.ACTION_START
                intent.putExtra("fileInfo", mFileInfo)
                Toast.makeText(mContext,"---------start---------",0).show();
                mContext!!.startService(intent)
            }
            viewHolder.stopButton!!.setOnClickListener {
                val intent = Intent(mContext, DownloadService::class.java)
                intent.action = DownloadService.ACTION_STOP
                intent.putExtra("fileInfo", mFileInfo)
                mContext!!.startService(intent)
            }

            convertView.tag = viewHolder
        } else {
            viewHolder = convertView.tag as ViewHolder
        }


        viewHolder.progressBar!!.progress = mFileInfo.finished


        return convertView
    }

    fun updataProgress(id: Int, progress: Int) {
        val info = mFilelist!![id]
        info.finished = progress
        notifyDataSetChanged()
    }

    internal class ViewHolder {
        var textview: TextView? = null
        var startButton: Button? = null
        var stopButton: Button? = null
        var progressBar: ProgressBar? = null
    }

}
