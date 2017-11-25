package com.bwie.sss.bean

/**
 * Created by 苏康泰 on 2017/11/23.
 */
class LoginBean {

data class LoginBean(
		val msg: String, //登录成功
		val code: String, //0
		val data: Data
)

data class Data(
		val age: Any, //null
		val appkey: String, //18837e92a66298c7
		val appsecret: String, //31B51EC5E97EC2416B03EAF547D19BE3
		val createtime: String, //2017-11-23T16:42:54
		val email: Any, //null
		val gender: Any, //null
		val icon: Any, //null
		val mobile: String, //18801220724
		val money: Any, //null
		val nickname: Any, //null
		val password: String, //8963E3628C6300E9AEEC86C476A15EBE
		val token: String, //4936BCA1CFC7C3340327BBA35BE8FB3D
		val uid: Int, //2693
		val username: String //18801220724
)
}