package com.leatien.lepaikt.business.activity

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import com.flyco.tablayout.listener.CustomTabEntity
import com.flyco.tablayout.listener.OnTabSelectListener
import com.leatien.lepaikt.R
import com.leatien.lepaikt.adapter.PagerAdapter
import com.leatien.lepaikt.base.view.BaseMvpActivity
import com.leatien.lepaikt.bean.TabEntity
import com.leatien.lepaikt.business.fragment.OrderCommonFragment
import kotlinx.android.synthetic.main.activity_order_main.*

/**
 * Created by oyh on 2018/12/29
 * 订单主页面
 */
class OrderMainActivity : BaseMvpActivity(), ProductIsAuctionDetailContract.View {
    private val mTitles = arrayOf("正在拍", "已出局", "待付款", "待收货", "已完成")
    private val mIconUnSelectIds = intArrayOf(R.mipmap.ico_is_auction,
            R.mipmap.ico_is_out,
            R.mipmap.ico_for_the_payment,
            R.mipmap.ico_for_the_goods,
            R.mipmap.ico_is_complete)
    private val mIconSelectIds = intArrayOf(R.mipmap.ico_is_auction,
            R.mipmap.ico_is_out,
            R.mipmap.ico_for_the_payment,
            R.mipmap.ico_for_the_goods,
            R.mipmap.ico_is_complete)
    private val mFragment = arrayListOf<Fragment>(OrderCommonFragment.newInstance(),
            OrderCommonFragment.newInstance(),
            OrderCommonFragment.newInstance(),
            OrderCommonFragment.newInstance(),
            OrderCommonFragment.newInstance())
    private val mTabEntities = arrayListOf<CustomTabEntity>()
    private var pagerAdapter:PagerAdapter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order_main)
        initData()
    }

    private fun initData() {
        bottomView.setItemSelectCallBack {
            when(it){
                0 -> startActivity(Intent(this,HomePageActivity::class.java))
                1 -> return@setItemSelectCallBack
            }
        }
        for (i in mTitles.indices) {
            mTabEntities.add(TabEntity(mTitles[i], mIconSelectIds[i], mIconUnSelectIds[i]))
        }
        tabSelect.setTabData(mTabEntities)
        pagerAdapter = PagerAdapter(supportFragmentManager,mFragment)
        vpDetailPage.adapter = pagerAdapter
        tabSelect.setOnTabSelectListener(object :OnTabSelectListener{
            override fun onTabSelect(position: Int) {
                vpDetailPage.currentItem = position
            }

            override fun onTabReselect(position: Int) {
            }
        })

        vpDetailPage.addOnPageChangeListener(object :ViewPager.OnPageChangeListener{
            override fun onPageScrollStateChanged(p0: Int) {
            }

            override fun onPageScrolled(p0: Int, p1: Float, p2: Int) {
            }

            override fun onPageSelected(p0: Int) {
                tabSelect.currentTab = p0
            }
        })
    }
}
