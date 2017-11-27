package com.bwie.sss.bean

import org.greenrobot.greendao.annotation.Entity
import org.greenrobot.greendao.annotation.Id
import org.greenrobot.greendao.annotation.Property

@Entity
class ThreadInfo {
    @Id
    var id: Int = 0
    @Property(nameInDb = "url")
    var url: String? = null
    var start: Int = 0
    var end: Int = 0
    var finished: Int = 0

    constructor() : super() {}

    constructor(id: Int, url: String, start: Int, end: Int, finished: Int) : super() {
        this.id = id
        this.url = url
        this.start = start
        this.end = end
        this.finished = finished
    }

    override fun toString(): String {
        return ("ThreadInfo [id=" + id + ", url=" + url + ", start=" + start + ", end=" + end + ", finished=" + finished
                + "]")
    }

}
