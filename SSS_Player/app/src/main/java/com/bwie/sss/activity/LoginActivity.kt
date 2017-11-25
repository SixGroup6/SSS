package com.bwie.sss.activity

import android.content.Intent
import android.view.View
import android.view.View.OnClickListener
import android.widget.*
import com.bwie.sss.R
import com.bwie.sss.presenter.LoginPresenter
import com.bwie.sss.view.LoginViewInterface

class LoginActivity : BaseActivity<LoginViewInterface, LoginPresenter>(),LoginViewInterface, OnClickListener{
    override fun getLayout(): Int {
        return R.layout.activity_login
    }


    var mobile=""
    var password=""
    lateinit var my_login_user:EditText
    lateinit var my_login_pwd:EditText
    lateinit var my_login:Button
    lateinit var my_login_return:ImageView
    lateinit var my_change_register:TextView



    override fun getPresenter(): LoginPresenter {
      return LoginPresenter(this)
    }

    override fun initData() {
        my_change_register=findViewById(R.id.my_change_register)as TextView;
        my_login_user= findViewById(R.id.my_login_user)as EditText
        my_login_pwd = findViewById(R.id.my_login_pwd) as EditText
        my_login = findViewById(R.id.my_login) as Button
        my_login_return = findViewById(R.id.my_login_return) as ImageView
        var loginpresenter = getPresenter();
        loginpresenter=LoginPresenter(this)
        my_login.setOnClickListener({
            mobile=my_login_user.text.toString()
            password=my_login_pwd.text.toString()
            loginpresenter.login(mobile,password,this)
        })
        my_change_register.setOnClickListener(OnClickListener {
            intent= Intent()
            intent.setClass(this,RegisterActivity::class.java)
            startActivity(intent)
        })

    }
    override fun onClick(v: View?) {

    }

    override fun onSuccess() {
        Toast.makeText(this,"登录成功",Toast.LENGTH_SHORT).show()
        intent=Intent()
        intent.setClass(this,MainActivity::class.java)
        startActivity(intent)
    }

    override fun onError() {

        Toast.makeText(this,"登录失败",Toast.LENGTH_SHORT).show()
    }

    override fun setLoginUserError() {
        my_login_user.setError("can not empty")
    }

    override fun setLoginPwdError() {
        my_login_pwd.setError("can not empty")
    }


}
