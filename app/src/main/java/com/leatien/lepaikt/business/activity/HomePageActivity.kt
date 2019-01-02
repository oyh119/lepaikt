package com.leatien.lepaikt.business.activity

import android.content.Intent
import android.os.Bundle
import android.support.v4.view.ViewPager
import com.flyco.tablayout.listener.OnTabSelectListener
import com.leatien.lepaikt.R
import com.leatien.lepaikt.adapter.PagerAdapter
import com.leatien.lepaikt.base.view.BaseMvpActivity
import com.leatien.lepaikt.business.fragment.ProductCompleteAuctionFragment
import com.leatien.lepaikt.business.fragment.ProductDetailIsAuctionFragment
import com.leatien.lepaikt.common.GlideImageLoaderEntity
import com.youth.banner.BannerConfig
import kotlinx.android.synthetic.main.activity_home_page.*

class HomePageActivity : BaseMvpActivity(), HomePageContract.View {
    private val mTitles = arrayOf("正在拍卖", "拍卖预告", "往期拍卖")
    private var mImages: MutableList<String> = arrayListOf()
    private val frag = listOf(ProductDetailIsAuctionFragment(), ProductDetailIsAuctionFragment(),ProductCompleteAuctionFragment())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_page)
        initData()
    }

    private fun initData() {
        mImages.add("http://ww4.sinaimg.cn/large/006uZZy8jw1faic1xjab4j30ci08cjrv.jpg")
        mImages.add("http://ww4.sinaimg.cn/large/006uZZy8jw1faic21363tj30ci08ct96.jpg")
        mImages.add("https://desk-fd.zol-img.com.cn/t_s1024x768c5/g4/M02/0A/07/Cg-4y1Tz-0-ILrRaAA1Il0Zj-8QAAV4SgAAAAAADUiv381.jpg")
        mImages.add("http://ww4.sinaimg.cn/large/006uZZy8jw1faic2e7vsaj30ci08cglz.jpg")

        productBanner.setImageLoader(GlideImageLoaderEntity())
                .isAutoPlay(true)
                .setDelayTime(3000)
                .setImages(mImages)
                .setIndicatorGravity(BannerConfig.CENTER)
        productBanner.setOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(i: Int, v: Float, i1: Int) {
                //                ToastUtils.showToast(getActivity(), "滚动到第" + i + "图片");
            }

            override fun onPageSelected(i: Int) {
                //                ToastUtils.showToast(getActivity(), "点击第" + i + "图片");
            }

            override fun onPageScrollStateChanged(i: Int) {

            }
        })
        productBanner.start()
        viewPager.adapter = PagerAdapter(supportFragmentManager,frag)
        viewPager.offscreenPageLimit = 3
        tableLayout.setTabData(mTitles)
        viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener{
            override fun onPageScrollStateChanged(p0: Int) {

            }

            override fun onPageScrolled(p0: Int, p1: Float, p2: Int) {
            }

            override fun onPageSelected(p0: Int) {
                tableLayout.currentTab = p0
            }

        })
        tableLayout.setOnTabSelectListener(object : OnTabSelectListener{
            override fun onTabSelect(position: Int) {
                viewPager.currentItem = position
            }

            override fun onTabReselect(position: Int) {
            }

        })
        bottomView.setItemSelectCallBack {
            when(it){
                0 -> return@setItemSelectCallBack
                1 -> startActivity(Intent(this,OrderMainActivity::class.java))
            }
        }
    }
}
