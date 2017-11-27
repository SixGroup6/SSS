package com.bwie.sss.bean

import java.io.Serializable

/**
 * 1.类的用途
 * 2.@authorDell
 * 3.@date2017/11/27 14:54
 */

class FileIn : Serializable{
    var id: Int = 0
    var url: String? = null
    var fileName: String? = null
    var length: Int = 0
    var finished: Int = 0

    constructor() : super() {}

    /**
     *
     * @param id   �ļ���ID
     * @param url  �ļ������d��ַ
     * @param fileName  �ļ�������
     * @param length  �ļ��Ŀ���С
     * @param finished  �ļ��ѽ�����˶���
     */
    constructor(id: Int, url: String, fileName: String, length: Int, finished: Int) : super() {
        this.id = id
        this.url = url
        this.fileName = fileName
        this.length = length
        this.finished = finished
    }

    override fun toString(): String {
        return ("FileIn [id=" + id + ", url=" + url + ", fileName=" + fileName + ", length=" + length + ", finished="
                + finished + "]")
    }
}
