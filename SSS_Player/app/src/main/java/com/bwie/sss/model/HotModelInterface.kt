package com.bwie.sss.model

import android.content.Context
import com.bwie.sss.bean.HotBean
import io.reactivex.Observable

/**
 * Created by Dell on 2017/11/30.
 */
interface HotModelInterface {

    fun loadData(context: Context , strategy:String?): Observable<HotBean>
}