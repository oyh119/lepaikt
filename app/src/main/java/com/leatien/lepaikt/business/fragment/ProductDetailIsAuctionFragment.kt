package com.leatien.lepaikt.business.fragment

import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.leatien.lepaikt.R
import com.leatien.lepaikt.adapter.ProductDetailAdapter
import com.leatien.lepaikt.bean.ProductDetailBean
import com.leatien.lepaikt.business.activity.ProductIsAuctionDetailActivity
import com.leatien.lepaikt.common.functions.Action1
import com.trello.rxlifecycle2.components.support.RxFragment
import kotlinx.android.synthetic.main.fragment_product_detail_is_auction.*
import org.jetbrains.anko.toast

/**
 * Created by oyh on 2018/12/20
 */
class ProductDetailIsAuctionFragment : RxFragment() {
    private var adapter:ProductDetailAdapter? = null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_product_detail_is_auction, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initData(view)
    }

    private fun initData(view: View) {
        val list = arrayListOf<ProductDetailBean>()
        for (i in 0..5) {
            val bean = ProductDetailBean(if(i == 0)"00:00:59" else "02:03:50")
            list.add(bean)
        }
        adapter = ProductDetailAdapter(view.context,ProductDetailAdapter.IS_AUCTION_PAGE_FLAG, Action1 {
            activity?.toast(it.toString())//activity为空就不执行右边部分
            startActivity(Intent(activity,ProductIsAuctionDetailActivity::class.java))
//            activity!!.toast(it.toString())//确定activity不为空，如果activity为空就会报空指针
        })
        adapter?.setData(list)
        rcy_product_detail_list.layoutManager = LinearLayoutManager(activity,LinearLayoutManager.VERTICAL,false)
        rcy_product_detail_list.adapter = adapter

    }

    override fun onDestroyView() {
        super.onDestroyView()
        adapter?.cancelAllTimers()
    }

    override fun onDestroy() {
        super.onDestroy()
        adapter!!.cancelAllTimers()
    }
}
