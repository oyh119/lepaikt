package com.leatien.lepaikt.business.activity

import android.content.Intent
import android.graphics.Paint
import android.os.Bundle
import com.leatien.lepaikt.base.view.BaseMvpActivity
import com.leatien.lepaikt.common.msg
import com.leatien.lepaikt.R
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.*
import javax.inject.Inject
/**
 * Created by oyh on 2018/12/12
 * 登录页面
 */
class MainActivity : BaseMvpActivity() , MainContract.View {

    //presenter为非空变量，kotlin要求给默认值，但是我们用的是注入方式赋值，所以使用lateinit标记为延后赋值
    @Inject
    lateinit var presenter: MainContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setTitle(R.string.user_login_tips)
        initData()
    }

    private fun initData() {
        registerAccount.paintFlags = Paint.UNDERLINE_TEXT_FLAG
        registerAccount.setOnClickListener{
//            startActivity(Intent(this, LoginActivity::class.java).putExtra("1", 1))
            startActivity(Intent(this,RegisterActivity::class.java))
        }

        loginBtn.setOnClickListener{
            startActivity<HomePageActivity>()
        }
    }

    fun login(name: CharSequence?,pwd: CharSequence?){
        presenter.login(name.toString(),pwd.toString(),{
            toast(it.msg())
        }){
//            startActivity<LoginActivity>()
        }
    }
}

