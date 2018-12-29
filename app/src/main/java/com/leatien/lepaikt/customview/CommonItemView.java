package com.leatien.lepaikt.customview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.jakewharton.rxbinding2.view.RxView;
import com.leatien.lepaikt.R;
import com.leatien.lepaikt.common.functions.Action1;

import java.util.concurrent.TimeUnit;

import io.reactivex.android.schedulers.AndroidSchedulers;


/**
 * Created by oyh on 2018/11/30
 * 个人中心，通用title item切换布局view
 */
public class CommonItemView extends LinearLayout {
    View rootView;
    TextView mIsAuction;
    TextView mIsOut;
    TextView mThePayment;
    TextView mTheGoods;
    TextView mIsComplete;
    Action1<Integer> itemSelectCallBack;

    public CommonItemView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context, attrs,0);
    }
    public CommonItemView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context, attrs, defStyleAttr);
    }

    private void initView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        rootView = LayoutInflater.from(context).inflate(R.layout.view_common_title_item_view, this, true);
        mIsAuction = rootView.findViewById(R.id.tv_is_auction);
        mIsOut = rootView.findViewById(R.id.tv_is_out);
        mThePayment = rootView.findViewById(R.id.tv_the_payment);
        mTheGoods = rootView.findViewById(R.id.tv_the_goods);
        mIsComplete = rootView.findViewById(R.id.tv_is_complete);
        TypedArray array = context.getTheme().obtainStyledAttributes(attrs
                , R.styleable.CommonItemView
                , defStyleAttr, 0);
        int defaultType = array.getInt(R.styleable.CommonItemView_defaultItem, -1);
        array.recycle();

        switch (defaultType) {
            case 0:
                mIsAuction.setBackgroundResource(R.drawable.shape_bottom_line_view_a927737);
                break;
            case 1:
                mIsOut.setBackgroundResource(R.drawable.shape_bottom_line_view_a927737);
                break;
            case 2:
                mThePayment.setBackgroundResource(R.drawable.shape_bottom_line_view_a927737);
                break;
            case 3:
                mTheGoods.setBackgroundResource(R.drawable.shape_bottom_line_view_a927737);
                break;
            case 4:
                mIsComplete.setBackgroundResource(R.drawable.shape_bottom_line_view_a927737);
                break;
            default:
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    mIsAuction.setBackgroundResource(R.drawable.shape_common_white_bg);
                    mIsOut.setBackgroundResource(R.drawable.shape_common_white_bg);
                    mThePayment.setBackgroundResource(R.drawable.shape_common_white_bg);
                    mTheGoods.setBackgroundResource(R.drawable.shape_common_white_bg);
                    mIsComplete.setBackgroundResource(R.drawable.shape_common_white_bg);
                }
                break;
        }
        initClick();
    }

    public void setCallBack(Action1<Integer> itemSelectCallBack){
        this.itemSelectCallBack = itemSelectCallBack;
    }

    @SuppressLint("CheckResult")
    private void initClick() {
        RxView.clicks(mIsAuction).throttleFirst(1,TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(v ->{
                    mIsAuction.setBackgroundResource(R.drawable.shape_bottom_line_view_a927737);
                    mIsOut.setBackgroundResource(R.drawable.shape_common_white_bg);
                    mThePayment.setBackgroundResource(R.drawable.shape_common_white_bg);
                    mTheGoods.setBackgroundResource(R.drawable.shape_common_white_bg);
                    mIsComplete.setBackgroundResource(R.drawable.shape_common_white_bg);
                    itemSelectCallBack.call(0);
                });
        RxView.clicks(mIsOut).throttleFirst(1,TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(v ->{
                    mIsAuction.setBackgroundResource(R.drawable.shape_common_white_bg);
                    mIsOut.setBackgroundResource(R.drawable.shape_bottom_line_view_a927737);
                    mThePayment.setBackgroundResource(R.drawable.shape_common_white_bg);
                    mTheGoods.setBackgroundResource(R.drawable.shape_common_white_bg);
                    mIsComplete.setBackgroundResource(R.drawable.shape_common_white_bg);
                    itemSelectCallBack.call(1);
                });
        RxView.clicks(mThePayment).throttleFirst(1,TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(v ->{
                    mIsAuction.setBackgroundResource(R.drawable.shape_common_white_bg);
                    mIsOut.setBackgroundResource(R.drawable.shape_common_white_bg);
                    mThePayment.setBackgroundResource(R.drawable.shape_bottom_line_view_a927737);
                    mTheGoods.setBackgroundResource(R.drawable.shape_common_white_bg);
                    mIsComplete.setBackgroundResource(R.drawable.shape_common_white_bg);
                    itemSelectCallBack.call(2);
                });
        RxView.clicks(mTheGoods).throttleFirst(1,TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(v ->{
                    mIsAuction.setBackgroundResource(R.drawable.shape_common_white_bg);
                    mIsOut.setBackgroundResource(R.drawable.shape_common_white_bg);
                    mThePayment.setBackgroundResource(R.drawable.shape_common_white_bg);
                    mTheGoods.setBackgroundResource(R.drawable.shape_bottom_line_view_a927737);
                    mIsComplete.setBackgroundResource(R.drawable.shape_common_white_bg);
                    itemSelectCallBack.call(3);
                });
        RxView.clicks(mIsComplete).throttleFirst(1,TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(v ->{
                    mIsAuction.setBackgroundResource(R.drawable.shape_common_white_bg);
                    mIsOut.setBackgroundResource(R.drawable.shape_common_white_bg);
                    mThePayment.setBackgroundResource(R.drawable.shape_common_white_bg);
                    mTheGoods.setBackgroundResource(R.drawable.shape_common_white_bg);
                    mIsComplete.setBackgroundResource(R.drawable.shape_bottom_line_view_a927737);
                    itemSelectCallBack.call(4);
                });
    }
}
