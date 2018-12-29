package com.leatien.lepaikt.customview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;


import com.jakewharton.rxbinding2.view.RxView;
import com.leatien.lepaikt.R;

import java.util.concurrent.TimeUnit;


/**
 * Created by oyh on 2018/11/27
 * 修改价格view
 */
public class ChangePriceView extends LinearLayout {
    View rootView;
    public ChangePriceView(Context context) {
        super(context);
        initView(context);
    }

    public ChangePriceView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public ChangePriceView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    @SuppressLint({"InflateParams", "CheckResult"})
    private void initView(Context context){
        ImageView reducePrice;
        EditText productPrice;
        ImageView plusPrice;
        rootView = LayoutInflater.from(context).inflate(R.layout.view_change_price_view,this,true);
        reducePrice = rootView.findViewById(R.id.img_reduce_price);
        productPrice = rootView.findViewById(R.id.edt_product_price);
        plusPrice = rootView.findViewById(R.id.img_plus_price);

        RxView.clicks(reducePrice).throttleFirst(200,TimeUnit.MILLISECONDS).subscribe(v ->{
            String price = productPrice.getText().toString().trim();
            if (!TextUtils.isEmpty(price) || Integer.parseInt(price) != 0){
                int newPrice = (Integer.parseInt(price) - 1) < 0?0:(Integer.parseInt(price) - 1);
                productPrice.setText(String.valueOf(newPrice));
            }
        });

        RxView.clicks(plusPrice).throttleFirst(200,TimeUnit.MILLISECONDS).subscribe(v ->{
            String price = productPrice.getText().toString().trim();
            if (!TextUtils.isEmpty(price)){
                int newPrice = (Integer.parseInt(price) + 1);
                productPrice.setText(String.valueOf(newPrice));
            }
        });
    }
}
