package com.leatien.lepaikt.business.activity

import android.os.Bundle
import com.leatien.lepaikt.R
import com.leatien.lepaikt.base.view.BaseMvpActivity
/**
 * Created by oyh on 2018/12/12
 * 注册
 */
class RegisterActivity : BaseMvpActivity() ,RegisterContract.View{

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        setTitle(R.string.user_register_tips)
    }
}
