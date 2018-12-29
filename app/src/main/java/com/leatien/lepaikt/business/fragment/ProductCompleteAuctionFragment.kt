package com.leatien.lepaikt.business.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.leatien.lepaikt.R
import com.leatien.lepaikt.base.view.BaseMvpFragment
import com.trello.rxlifecycle2.components.support.RxFragment

/**
 * Created by oyh on 2018/12/20
 */
class ProductCompleteAuctionFragment : RxFragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_product_complete_auction, container, false)
    }
}
