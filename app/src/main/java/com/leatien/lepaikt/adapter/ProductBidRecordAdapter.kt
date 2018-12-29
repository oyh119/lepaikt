package com.leatien.lepaikt.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.leatien.lepaikt.R
import kotlinx.android.synthetic.main.item_product_bid_record_view.view.*
/**
 * Created by oyh on 2018/12/29
 * 出价记录
 */
class ProductBidRecordAdapter(val context:Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): RecyclerView.ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_product_bid_record_view,p0,false))
    }

    override fun getItemCount(): Int {
        return 5
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ViewHolder).bidName.text = "小明"
        holder.bidStatus.text = "出局"
        holder.bidTime.text = "10:20"
        holder.bidMoney.text = "￥1000"
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var bidName:TextView = itemView.tv_bid_name
        var bidStatus:TextView = itemView.tv_bid_state
        var bidTime:TextView = itemView.tv_bid_time
        var bidMoney:TextView = itemView.tv_bid_money
    }
}