package com.huyhuynh.mypokedex.screen.main.activity.login

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders
import com.huyhuynh.mypokedex.BR
import com.huyhuynh.mypokedex.R
import com.huyhuynh.mypokedex.databinding.ActivityLoginBinding
import com.huyhuynh.mypokedex.screen.main.activity.mainactivity.MainActivity
import demo.com.weatherapp.screen.base.activity.BaseBindingActivity

class LoginActivity : BaseBindingActivity<ActivityLoginBinding, LoginActivityViewModel>() {
    override val bindingVariable: Int
        get() = BR.loginViewModel
    override val viewModel: LoginActivityViewModel
        get() = ViewModelProviders.of(this).get(LoginActivityViewModel::class.java)
    override val layoutResource: Int
        get() = R.layout.activity_login

    override fun initVariable(savedInstanceState: Bundle?) {
        viewDataBinding?.btnLogin?.setOnClickListener(this)
        viewDataBinding?.btnRegister?.setOnClickListener(this)
    }

    override fun initData(savedInstanceState: Bundle?) {

    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.btn_login -> {
                var email = viewModel.email.get()
                var pass = viewModel.pass.get()
                if (email=="huy" && pass=="qwe"){
                    val intent = Intent(this,MainActivity::class.java)
                    startActivity(intent)
                } else {
                    Toast.makeText(this,"Nhập email \"huy\" và password \"qwe\"",Toast.LENGTH_SHORT).show()
                }
            }
            R.id.btn_register -> {
                Toast.makeText(this,"Register Click",Toast.LENGTH_SHORT).show()
            }
        }
    }
}