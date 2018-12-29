package com.leatien.lepaikt.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.os.CountDownTimer
import android.support.v7.widget.RecyclerView
import android.util.SparseArray
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.leatien.lepaikt.R
import com.leatien.lepaikt.bean.ProductDetailBean
import com.leatien.lepaikt.common.functions.Action1
import com.leatien.lepaikt.common.utils.TimeUtils
import kotlinx.android.synthetic.main.item_product_detail_view.view.*

class ProductDetailAdapter(val context:Context, private val pageFlag: Int, private var callBack: Action1<Long>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    companion object {
        const val ITEM_VIEW = 1
        const val BOTTOM_VIEW = 2
        const val IS_AUCTION_PAGE_FLAG = 3//正在拍卖
        const val AUCTION_NOTICE_PAGE_FLAG = 4//拍卖预告
        const val COMPLETE_AUCTION_PAGE_FLAG = 5//往期拍卖
    }

    private val countDownMap = SparseArray<CountDownTimer>()
    private var tempTime: Long = 0
    var list:List<ProductDetailBean> = arrayListOf()
    override fun onCreateViewHolder(group: ViewGroup, position: Int): RecyclerView.ViewHolder {
        if (position == ITEM_VIEW)
            return ItemViewHolder(LayoutInflater.from(context).inflate(R.layout.item_product_detail_view, group, false))
        return BottomViewHolder(LayoutInflater.from(context).inflate(R.layout.item_product_detail_bottom_view,group,false))
    }

    fun setData(data: List<ProductDetailBean>) {
        list = data
        tempTime = System.currentTimeMillis()
        notifyDataSetChanged()
    }

    /**
     * 清空资源
     */
    fun cancelAllTimers() {
        var i = 0
        val length = countDownMap.size()
        while (i < length) {
            val cdt = countDownMap.get(countDownMap.keyAt(i))
            cdt?.cancel()//如果cdt不为null就执行这个语句
            i++
        }
    }

    override fun getItemCount(): Int {
        return list.size + 1
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == list.size) BOTTOM_VIEW else ITEM_VIEW
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (getItemViewType(position) == ITEM_VIEW){
            val productDetailBean : ProductDetailBean = list[position]
            (holder as ItemViewHolder).productName.text = "Apple Watch"
            holder.topPrice.text = "封顶价 : 10000"
            holder.margin.text = "保证金 : 12334"
            holder.startPrice.text = "起拍价 : 123445"
            val timeStamp = System.currentTimeMillis() - tempTime
            if (holder.countDownTimer != null) {//销毁前一个对象，以免因为holder的复用，导致页面闪动
                holder.countDownTimer!!.cancel()
            }
            if (pageFlag == COMPLETE_AUCTION_PAGE_FLAG) {
                holder.auctionTime.visibility = View.GONE
            } else {
                holder.auctionTime.visibility = View.VISIBLE
                val time = TimeUtils.dateToLong(productDetailBean.time) - timeStamp
                if (time > 0) {
                    holder.countDownTimer = object : CountDownTimer(time, 1000) {
                        override fun onTick(millisUntilFinished: Long) {
                            holder.auctionTime.text = "倒计时时间: " + TimeUtils.getCountTimeByLong(millisUntilFinished)
                        }

                        override fun onFinish() {
                            holder.auctionTime.text = "倒计时时间: 00:00:00"
                        }
                    }.start()
                    countDownMap.put(holder.auctionTime.hashCode(), holder.countDownTimer)
                } else {
                    holder.auctionTime.text = "倒计时时间: 00:00:00"
                }
            }

            holder.productIco.setOnClickListener{
                callBack.call(0)
            }
        }
    }

    class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var productName:TextView = itemView.tv_product_name
        var topPrice : TextView = itemView.tv_top_price
        var margin : TextView = itemView.tv_margin_money
        var startPrice: TextView = itemView.tv_open_bid
        var auctionTime : TextView = itemView.cd_auction_time
        internal var countDownTimer: CountDownTimer? = null
        var productIco:ImageView = itemView.img_product_ico
    }

    class BottomViewHolder(itemView: View) :RecyclerView.ViewHolder(itemView)

}