package com.leatien.lepaikt.base.view

import android.content.Context
import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.annotation.StringRes
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.gyf.barlibrary.ImmersionBar
import com.leatien.lepaikt.R
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity
import dagger.android.AndroidInjection
import org.jetbrains.anko.find

/**
 * mvp模式的Activity基类，集成至Relifecycle库提供的RxAppCompatActivity
 * 可以实现Rxjava链式操作绑定Activity生命周期
 */
open class BaseMvpActivity : RxAppCompatActivity() {
    lateinit var viewGroup:ViewGroup
    lateinit var mContent:LinearLayout
    private lateinit var titleView:View
    lateinit var pageBackImg:ImageView
    lateinit var baseTitleView:FrameLayout
    lateinit var title:TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        viewGroup = find(android.R.id.content)
        viewGroup.removeAllViews()
        mContent = LinearLayout(this)
        mContent.orientation = LinearLayout.VERTICAL
        viewGroup.addView(mContent)
        titleView = LayoutInflater.from(this).inflate(R.layout.activity_base_title, mContent, true)

        initView()
    }

    private fun initView() {
        title = find(R.id.tv_title)
        pageBackImg = find(R.id.img_btn_back)
        baseTitleView = find(R.id.base_activity_swipe_container)
        pageBackImg.setOnClickListener{ this.onNavBack(it) }
        initImmersionBar()
    }

    override fun setContentView(@LayoutRes layoutResID: Int) {
        LayoutInflater.from(this).inflate(layoutResID, mContent, true)
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN)
    }

    override fun setContentView(view: View) {
        mContent.addView(view)
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN)
    }

    fun getContent(): LinearLayout {
        return mContent
    }

    /**
     * 默认为退出当前页
     * 如有特殊处理，请在之类overwrite这个方法
     */
    private fun onNavBack(view: View) {
        finish()
    }

    fun hideTitleView() {
        baseTitleView.visibility = View.GONE
    }

    fun showTitleView() {
        baseTitleView.visibility = View.VISIBLE
    }

    fun setTitle(str: String) {
        title.text = str
    }

    override fun setTitle(@StringRes id: Int) {
        title.text = resources.getString(id)
    }

    private fun initImmersionBar() {
        //在BaseActivity里初始化
        ImmersionBar.with(this).statusBarColor(R.color.c1a000000).fitsSystemWindows(true).init()
    }

    protected override fun onDestroy() {
        super.onDestroy()
        ImmersionBar.with(this).destroy()
    }

    /**
     * 隐藏系统的软键盘
     */
    fun hideSysInput() {
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(mContent.windowToken, InputMethodManager.HIDE_NOT_ALWAYS)
    }
}
