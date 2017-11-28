package com.bwie.sss.bean

import org.greenrobot.greendao.annotation.Entity
import org.greenrobot.greendao.annotation.Id
import org.greenrobot.greendao.annotation.Property
import org.greenrobot.greendao.annotation.Generated

import java.io.Serializable

/**
 * 1:类的用途
 * 2：@author Dell
 * 3：@date 2017/11/22
 */
@Entity
class FileInfoBean : Serializable {
    @Id
    var id: Long = 0
    @Property(nameInDb = "url")
    var url: String? = null //URL
    @Property(nameInDb = "length")
    var length: Int = 0 //长度或结束位置
    @Property(nameInDb = "start")
    var start: Int = 0 //开始位置
    @Property(nameInDb = "now")
    var now: Int = 0//当前进度

    @Generated(hash = 721743383)
    constructor(id: Long, url: String, length: Int, start: Int, now: Int) {
        this.id = id
        this.url = url
        this.length = length
        this.start = start
        this.now = now
    }

    @Generated(hash = 410787233)
    constructor() {
    }
}
