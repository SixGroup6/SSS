package com.bwie.sss.activity

import android.content.Intent
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import com.bwie.sss.R
import com.bwie.sss.presenter.RegisterPresenter
import com.bwie.sss.view.RegisterViewInterface

class RegisterActivity : BaseActivity<RegisterViewInterface, RegisterPresenter>(),RegisterViewInterface {
    override fun getLayout(): Int {
       return R.layout.activity_register
    }


    var username = ""
    var password = ""
    lateinit var my_register_user: EditText
    lateinit var my_register_pwd: EditText
    lateinit var my_register: Button
    lateinit var my_register_return: ImageView




    override fun getPresenter(): RegisterPresenter {
        return RegisterPresenter(this)
    }

    override fun initData() {
        my_register_user = findViewById(R.id.my_register_user) as EditText
        my_register_pwd = findViewById(R.id.my_register_pwd) as EditText
        my_register = findViewById(R.id.my_register) as Button
        my_register_return = findViewById(R.id.my_register_return) as ImageView
        val registerpresenter = getPresenter()

        my_register.setOnClickListener({
            username = my_register_user.text.toString()
            password = my_register_pwd.text.toString()

            registerpresenter.register(username, password, this)
        })
        my_register_return.setOnClickListener({
            finish()
        })
    }


    override fun setRegisterUserError() {
        my_register_user.setError("can not empty")
    }

    override fun setRegisterPwdError() {
        my_register_pwd.setError("can not empty")
    }

    override fun onSuccess() {
        Toast.makeText(this, "注册成功", Toast.LENGTH_SHORT).show()
        val intent = Intent()
        intent.setClass(this, LoginActivity::class.java)
        startActivity(intent)


    }

    override fun onWrong() {

        Toast.makeText(this, "失败", Toast.LENGTH_SHORT).show()
    }

}
