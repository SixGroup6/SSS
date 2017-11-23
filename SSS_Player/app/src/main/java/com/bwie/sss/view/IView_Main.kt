package com.bwie.sss.view

import com.bwie.sss.bean.UpDataBean
import com.bwie.sss.bean.VideoBean


/**
 * 1:类的用途
 * 2：@author Dell
 * 3：@date 2017/11/22
 */
interface IView_Main {
    fun setUpdata(upData: UpDataBean.UpData)

    fun setVideo(videoBean: VideoBean.Video)

}