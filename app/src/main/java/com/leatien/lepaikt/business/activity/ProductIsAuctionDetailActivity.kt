package com.leatien.lepaikt.business.activity

import android.graphics.Rect
import android.os.Build
import android.os.Bundle
import android.support.v4.view.ViewPager
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.view.ViewGroup
import com.leatien.lepaikt.R
import com.leatien.lepaikt.adapter.ProductBidRecordAdapter
import com.leatien.lepaikt.base.view.BaseMvpActivity
import com.leatien.lepaikt.common.GlideImageLoaderEntity
import com.youth.banner.BannerConfig
import kotlinx.android.synthetic.main.activity_product_is_auction_detail.*

/**
 * Created by oyh on 2018/12/29
 * 商品详情
 */
class ProductIsAuctionDetailActivity : BaseMvpActivity(), ProductIsAuctionDetailContract.View {
    private var mImages: MutableList<String> = arrayListOf()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_is_auction_detail)
        initData()
    }

    private fun initData() {
        mImages.add("http://ww4.sinaimg.cn/large/006uZZy8jw1faic1xjab4j30ci08cjrv.jpg")
        mImages.add("http://ww4.sinaimg.cn/large/006uZZy8jw1faic21363tj30ci08ct96.jpg")
        mImages.add("https://desk-fd.zol-img.com.cn/t_s1024x768c5/g4/M02/0A/07/Cg-4y1Tz-0-ILrRaAA1Il0Zj-8QAAV4SgAAAAAADUiv381.jpg")
        mImages.add("http://ww4.sinaimg.cn/large/006uZZy8jw1faic2e7vsaj30ci08cglz.jpg")

        product_detail_banner.setImageLoader(GlideImageLoaderEntity())
                .isAutoPlay(true)
                .setDelayTime(3000)
                .setImages(mImages)
                .setIndicatorGravity(BannerConfig.CENTER)
        product_detail_banner.setOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(i: Int, v: Float, i1: Int) {
                //                ToastUtils.showToast(getActivity(), "滚动到第" + i + "图片");
            }

            override fun onPageSelected(i: Int) {
                //                ToastUtils.showToast(getActivity(), "点击第" + i + "图片");
            }

            override fun onPageScrollStateChanged(i: Int) {

            }
        })
        product_detail_banner.start()

        tv_countdown.initTime(1,20,50)

        rcy_bid_record.layoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
        rcy_bid_record.adapter = ProductBidRecordAdapter(this)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            scoll_view.setOnScrollChangeListener { v, scrollX, scrollY, oldScrollX, oldScrollY ->
                if (isViewCovered(tv_product_price)) conlyt_current_price_view.visibility = View.VISIBLE
                else conlyt_current_price_view.visibility = View.GONE
            }
        }
    }

    /**
     * 判断指定的view是否在屏幕内显示
     * @param view 指定的view
     * @return 是否显示
     */
    private fun isViewCovered(view: View): Boolean {
        var currentView = view

        val currentViewRect = Rect()
        val partVisible = currentView.getGlobalVisibleRect(currentViewRect)
        val totalHeightVisible = currentViewRect.bottom - currentViewRect.top >= view.measuredHeight
        val totalWidthVisible = currentViewRect.right - currentViewRect.left >= view.measuredWidth
        val totalViewVisible = partVisible && totalHeightVisible && totalWidthVisible
        // if any part of the view is clipped by any of its parents,return true
        if (!totalViewVisible)
            return true

        while (currentView.parent is ViewGroup) {
            val currentParent = currentView.parent as ViewGroup
            // if the parent of view is not visible,return true
            if (currentParent.visibility != View.VISIBLE)
                return true

            val start = indexOfViewInParent(currentView, currentParent)
            for (i in start + 1 until currentParent.childCount) {
                val viewRect = Rect()
                view.getGlobalVisibleRect(viewRect)
                val otherView = currentParent.getChildAt(i)
                val otherViewRect = Rect()
                otherView.getGlobalVisibleRect(otherViewRect)
                // if view intersects its older brother(covered),return true
                if (Rect.intersects(viewRect, otherViewRect))
                    return true
            }
            currentView = currentParent
        }
        return false
    }

    private fun indexOfViewInParent(view: View, parent: ViewGroup): Int {
        var index = 0
        while (index < parent.childCount) {
            if (parent.getChildAt(index) === view)
                break
            index++
        }
        return index
    }
}
