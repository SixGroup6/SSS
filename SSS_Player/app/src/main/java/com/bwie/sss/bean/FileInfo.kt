package com.bwie.sss.bean

import org.greenrobot.greendao.annotation.Entity

/**
 * 1:类的用途
 * 2：@author Dell
 * 3：@date 2017/11/22
 */
@Entity
class FileInfo {
    var length : Int? = null

    constructor(length: Int?) {
        this.length = length
    }
}