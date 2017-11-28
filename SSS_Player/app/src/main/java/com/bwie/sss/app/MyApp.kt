package com.bwie.sss.app

import android.app.Application
import android.database.sqlite.SQLiteDatabase
import android.support.multidex.MultiDexApplication
import com.bwie.sss.greendao.DaoMaster
import com.bwie.sss.greendao.DaoSession

/**
 * 1:类的用途
 * 2：@author Dell
 * 3：@date 2017/11/22
 */
class MyApp : MultiDexApplication() {
    var mHelper : DaoMaster.DevOpenHelper? = null
    var db : SQLiteDatabase? = null
    var mDaoMaster : DaoMaster? = null
    var mDaoSession : DaoSession? = null
    companion object {
        var instance : MyApp? = null
        internal fun getInstance() : MyApp{
            return instance!!
        }
    }
    override fun onCreate() {
        super.onCreate()
        instance = this
        setDataBase()
    }

    fun getDataBase() : SQLiteDatabase{
        return db!!
    }

    fun getDataSession() : DaoSession{
        return mDaoSession!!
    }

    fun setDataBase(){
        mHelper = DaoMaster.DevOpenHelper(this,"notes-db", null)
        db = mHelper!!.writableDatabase
        mDaoMaster = DaoMaster(db)
        mDaoSession = mDaoMaster!!.newSession()
    }
}