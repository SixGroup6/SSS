package com.bwie.sss.util

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DecodeFormat
import com.bumptech.glide.load.engine.DiskCacheStrategy

/**
 * Created by lvruheng on 2017/7/6.
 */
class ImageLoadUtils{
    companion object{
        fun display(context: Context, imageView: ImageView?, url: String) {
            if (imageView == null) {
                throw IllegalArgumentException("argument error")
            }
            Glide.with(context).load(url)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .centerCrop()
                    .crossFade().into(imageView)
        }
        fun displayHigh(context: Context, imageView: ImageView?, url: String){
            if (imageView == null) {
                throw IllegalArgumentException("argument error")
            }
            Glide.with(context).load(url)
                    .asBitmap()
                    .format(DecodeFormat.PREFER_ARGB_8888)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .centerCrop()
                    .into(imageView)
        }
    }

}