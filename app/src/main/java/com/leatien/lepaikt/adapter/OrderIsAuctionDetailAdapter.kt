package com.leatien.lepaikt.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.leatien.lepaikt.R
import com.leatien.lepaikt.common.functions.Action1
import kotlinx.android.synthetic.main.item_is_auction_view.view.*

class OrderIsAuctionDetailAdapter(val context: Context, private var callBack:Action1<Long>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): RecyclerView.ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_is_auction_view,p0,false))
    }

    override fun getItemCount(): Int {
        return 5
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(p0: RecyclerView.ViewHolder, p1: Int) {
        (p0 as ViewHolder).orderNum.text = "订单编号: 123456"
        p0.resetTime.text = "剩余: 1:00:00"
        p0.orderStatus.text = "待付款"
        p0.orderFlag.text = "暂时领先"
        p0.createTimt.text = "时间: 2019-1-02"
        p0.goodsName.text = "手机"
        p0.goodsPrice.text = "￥123456"
        p0.goPayment.setOnClickListener{
            callBack.call(0L)
        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var orderNum:TextView = itemView.tv_order_num
        var resetTime:TextView = itemView.tv_the_rest_time
        var orderStatus:TextView = itemView.tv_order_status
        var orderFlag:Button = itemView.btn_order_flag
        var createTimt:TextView = itemView.tv_create_time
        var goodsImg:ImageView = itemView.img_product_img
        var goodsName:TextView = itemView.tv_product_name
        var goodsPrice:TextView = itemView.tv_product_price
        var goPayment:Button = itemView.btn_go_payment
    }

}