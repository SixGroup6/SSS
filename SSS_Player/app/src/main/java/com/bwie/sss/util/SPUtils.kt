package com.bwie.sss.util

import android.content.Context

/**
 * Created by 燕子 on 2017/11/27.
 */
class SpUtils(context: Context) {
    val prefs by lazy{context.getSharedPreferences("config", Context.MODE_PRIVATE)}
}