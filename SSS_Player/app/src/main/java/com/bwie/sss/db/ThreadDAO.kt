package com.bwie.sss.db


import com.bwie.sss.bean.ThreadInfo

/**
 * ����������Ľӿ��
 *
 */
interface ThreadDAO {

    fun insertThread(info: ThreadInfo)

    fun deleteThread(url: String)

    fun updateThread(url: String, thread_id: Int, finished: Int)

    fun queryThreads(url: String): List<ThreadInfo>

    fun isExists(url: String, threadId: Int): Boolean
}
