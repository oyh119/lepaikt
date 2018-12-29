package com.leatien.lepaikt.business.activity

import android.os.Bundle
import android.support.v4.view.ViewPager
import com.leatien.lepaikt.R
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
    }
}
