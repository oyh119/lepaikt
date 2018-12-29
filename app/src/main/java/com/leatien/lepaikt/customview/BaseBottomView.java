package com.leatien.lepaikt.customview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;


import com.jakewharton.rxbinding2.view.RxView;
import com.leatien.lepaikt.R;
import com.leatien.lepaikt.common.functions.Action1;

import java.util.concurrent.TimeUnit;


/**
 * Created by oyh on 2018/12/5
 * 通用底部导航栏
 */
public class BaseBottomView extends FrameLayout {
    View rootView;
    TextView mFirstItem;
    TextView mSecondItem;
    TextView mThirdItem;
    TextView mFourItem;
    Action1<Integer> itemSelectCallBack;
    private int firstSelectResourceId;
    private int firstUnSelectResourceId;
    private int secondSelectIcon;
    private int secondUnSelectIcon;
    private int thirdSelectIcon;
    private int thirdUnSelectIcon;
    private int selectColor;
    private int unSelectColor;
    private Context context;
    // TODO: 2018/12/6 这两个属性留着以后如果要变化的话就相应的改
    private int fourSelectIcon;
    private int fourUnSelectIcon;

    public BaseBottomView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context,attrs,0);
    }

    public BaseBottomView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context,attrs,defStyleAttr);
    }

    @SuppressLint("CheckResult")
    private void initView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        this.context = context;
        rootView = LayoutInflater.from(context).inflate(R.layout.view_base_bottom_view, this, true);
        mFirstItem = rootView.findViewById(R.id.tv_item_first);
        mSecondItem = rootView.findViewById(R.id.tv_item_second);
        mThirdItem = rootView.findViewById(R.id.tv_item_third);
        mFourItem = rootView.findViewById(R.id.tv_item_four);
        TypedArray array = context.getTheme().obtainStyledAttributes(attrs
                , R.styleable.BaseBottomView
                , defStyleAttr, 0);
        int defaultIcon = array.getInt(R.styleable.BaseBottomView_defaultIcon, 0);
        firstSelectResourceId = array.getResourceId(R.styleable.BaseBottomView_firstSelectIcon,-1);
        firstUnSelectResourceId = array.getResourceId(R.styleable.BaseBottomView_firstUnSelectIcon,-1);
        secondSelectIcon = array.getResourceId(R.styleable.BaseBottomView_secondSelectIcon, -1);
        secondUnSelectIcon = array.getResourceId(R.styleable.BaseBottomView_secondUnSelectIcon, -1);
        thirdSelectIcon = array.getResourceId(R.styleable.BaseBottomView_thirdSelectIcon, -1);
        thirdUnSelectIcon = array.getResourceId(R.styleable.BaseBottomView_thirdUnSelectIcon, -1);
        fourSelectIcon = array.getResourceId(R.styleable.BaseBottomView_fourSelectIcon, -1);
        fourUnSelectIcon = array.getResourceId(R.styleable.BaseBottomView_fourUnSelectIcon,-1);
        selectColor = array.getResourceId(R.styleable.BaseBottomView_selectColor,-1);
        unSelectColor = array.getResourceId(R.styleable.BaseBottomView_unSelectColor,-1);
        array.recycle();

        switch (defaultIcon){
            case 0:
                setFirstColorAndIcon(context, firstSelectResourceId, secondUnSelectIcon, thirdUnSelectIcon, selectColor, unSelectColor, getResources().getColor(unSelectColor));
                break;
            case 1:
                setSecondColorAndIcon(context, firstUnSelectResourceId, secondSelectIcon, thirdUnSelectIcon, selectColor, unSelectColor, getResources().getColor(unSelectColor));
                break;
            case 2:
                setThirdColorAndIcon(context, firstUnSelectResourceId, secondUnSelectIcon, thirdSelectIcon, unSelectColor, getResources().getColor(unSelectColor), getResources().getColor(selectColor));
                break;
        }

        RxView.clicks(mFirstItem).throttleFirst(500,TimeUnit.MILLISECONDS)
                .subscribe(v ->{
                    setFirstColorAndIcon(context, firstSelectResourceId, secondUnSelectIcon, thirdUnSelectIcon, selectColor, unSelectColor, getResources().getColor(unSelectColor));
                    itemSelectCallBack.call(0);
                });
        RxView.clicks(mSecondItem).throttleFirst(500,TimeUnit.MILLISECONDS)
                .subscribe(v ->{
                    setSecondColorAndIcon(context, firstUnSelectResourceId, secondSelectIcon, thirdUnSelectIcon, selectColor, unSelectColor, getResources().getColor(unSelectColor));
                    itemSelectCallBack.call(1);
                });
        RxView.clicks(mThirdItem).throttleFirst(500,TimeUnit.MILLISECONDS)
                .subscribe(v ->{
                    setThirdColorAndIcon(context, firstUnSelectResourceId, secondUnSelectIcon, thirdSelectIcon, unSelectColor, getResources().getColor(unSelectColor), getResources().getColor(selectColor));
                    itemSelectCallBack.call(2);
                });
        RxView.clicks(mFourItem).throttleFirst(500,TimeUnit.MILLISECONDS)
                .subscribe(v ->{
//                    ToastUtils.showToast(context,"分享");
                });
    }

    public void setItemSelectCallBack(Action1<Integer> itemSelectCallBack) {
        this.itemSelectCallBack = itemSelectCallBack;
    }

    private void setThirdColorAndIcon(Context context, int firstUnSelectResourceId, int secondUnSelectIcon, int thirdSelectIcon, int unSelectColor, int color, int color2) {
        Drawable drawable3 = context.getResources().getDrawable(firstUnSelectResourceId);
        Drawable drawable4 = context.getResources().getDrawable(secondUnSelectIcon);
        Drawable drawable5 = context.getResources().getDrawable(thirdSelectIcon);
        drawable3.setBounds(0, 0, drawable3.getMinimumWidth(), drawable3.getMinimumHeight());
        drawable4.setBounds(0, 0, drawable4.getMinimumWidth(), drawable4.getMinimumHeight());
        drawable5.setBounds(0, 0, drawable5.getMinimumWidth(), drawable5.getMinimumHeight());
        mFirstItem.setCompoundDrawables(null, drawable3, null, null);
        mSecondItem.setCompoundDrawables(null, drawable4, null, null);
        mThirdItem.setCompoundDrawables(null, drawable5, null, null);

        mFirstItem.setTextColor(getResources().getColor(unSelectColor));
        mSecondItem.setTextColor(color);
        mThirdItem.setTextColor(color2);
    }

    private void setSecondColorAndIcon(Context context, int firstUnSelectResourceId, int secondSelectIcon, int thirdUnSelectIcon, int selectColor, int unSelectColor, int color) {
        Drawable d = context.getResources().getDrawable(firstUnSelectResourceId);
        Drawable dr = context.getResources().getDrawable(secondSelectIcon);
        Drawable draw = context.getResources().getDrawable(thirdUnSelectIcon);
        d.setBounds(0, 0, d.getMinimumWidth(), d.getMinimumHeight());
        dr.setBounds(0, 0, dr.getMinimumWidth(), dr.getMinimumHeight());
        draw.setBounds(0, 0, draw.getMinimumWidth(), draw.getMinimumHeight());
        mFirstItem.setCompoundDrawables(null, d, null, null);
        mSecondItem.setCompoundDrawables(null, dr, null, null);
        mThirdItem.setCompoundDrawables(null, draw, null, null);

        mFirstItem.setTextColor(getResources().getColor(unSelectColor));
        mSecondItem.setTextColor(getResources().getColor(selectColor));
        mThirdItem.setTextColor(color);
    }

    private void setFirstColorAndIcon(Context context, int firstSelectResourceId, int secondUnSelectIcon, int thirdUnSelectIcon, int selectColor, int unSelectColor, int color) {
        Drawable drawable = context.getResources().getDrawable(firstSelectResourceId);
        Drawable drawable1 = context.getResources().getDrawable(secondUnSelectIcon);
        Drawable drawable2 = context.getResources().getDrawable(thirdUnSelectIcon);
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        drawable1.setBounds(0, 0, drawable1.getMinimumWidth(), drawable1.getMinimumHeight());
        drawable2.setBounds(0, 0, drawable2.getMinimumWidth(), drawable2.getMinimumHeight());
        mFirstItem.setCompoundDrawables(null, drawable, null, null);
        mSecondItem.setCompoundDrawables(null, drawable1, null, null);
        mThirdItem.setCompoundDrawables(null, drawable2, null, null);

        mFirstItem.setTextColor(getResources().getColor(selectColor));
        mSecondItem.setTextColor(getResources().getColor(unSelectColor));
        mThirdItem.setTextColor(color);
    }

    public void resetSelectStatus(int position){
        switch (position){
            case 0:
                setFirstColorAndIcon(context, firstSelectResourceId, secondUnSelectIcon, thirdUnSelectIcon, selectColor, unSelectColor, getResources().getColor(unSelectColor));
                break;
            case 1:
                setSecondColorAndIcon(context, firstUnSelectResourceId, secondSelectIcon, thirdUnSelectIcon, selectColor, unSelectColor, getResources().getColor(unSelectColor));
                break;
            case 2:
                setThirdColorAndIcon(context, firstUnSelectResourceId, secondUnSelectIcon, thirdSelectIcon, unSelectColor, getResources().getColor(unSelectColor), getResources().getColor(selectColor));
                break;

        }
    }
}
