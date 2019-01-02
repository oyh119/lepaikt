package com.leatien.lepaikt.business.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.leatien.lepaikt.R
import com.leatien.lepaikt.adapter.OrderIsAuctionDetailAdapter
import com.leatien.lepaikt.common.functions.Action1
import com.leatien.lepaikt.common.logd
import kotlinx.android.synthetic.main.fragment_order_common.view.*

/**
 * Created by oyh on 2019/1/2
 */
class OrderCommonFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
       val view = inflater.inflate(R.layout.fragment_order_common, container, false)
        initData(view)
        return view
    }

    private fun initData(view: View) {
        view.orderDetailRcy.layoutManager = LinearLayoutManager(activity,LinearLayoutManager.VERTICAL,false)
        view.orderDetailRcy.adapter = OrderIsAuctionDetailAdapter(activity!!, Action1 {
            logd { "付款" }
        })

    }

    companion object {
        @JvmStatic
        fun newInstance() = OrderCommonFragment()
    }
}
