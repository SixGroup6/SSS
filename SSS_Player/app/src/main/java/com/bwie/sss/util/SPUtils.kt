package com.bwie.sss.util

import android.content.Context

/**
 * 1:类的用途
 * 2：@author Dell
 * 3：@date 2017/11/27
 */
class SpUtils(context: Context) {
    val prefs by lazy{context.getSharedPreferences("config",Context.MODE_PRIVATE)}
}