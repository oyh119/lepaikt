package com.leatien.lepaikt.customview;

import android.app.Activity;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.leatien.lepaikt.R;


/**
 * Created by oyh on 2018/11/29
 */
public abstract class OverLayer {
    View alertView;
    ViewGroup decor;
    boolean fadeDismisss = true;
    protected ViewGroup container;

    protected Context context;
    public OverLayer(Context context){
        this.context = context;
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        decor = (ViewGroup) ((Activity)context).getWindow().getDecorView().findViewById(android.R.id.content);
        container = (ViewGroup)layoutInflater.inflate(R.layout.view_overlayer,decor,false);
        container.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        alertView = getView(context,container);
        FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) alertView.getLayoutParams();
        params.gravity = Gravity.CENTER;
        alertView.setLayoutParams(params);
        container.addView(alertView);
        container.setOnClickListener(v -> {
            if (fadeDismisss) {
                dismiss();
            }
        });

     }

    public void disableFadeDismiss(){
        fadeDismisss = false;
    }

    public void show(){
        if (container.getParent()!=null)
            ((ViewGroup)container.getParent()).removeView(container);
        decor.addView(container);
    }

    public void dismiss(){
        decor.removeView(container);

    }
    public abstract View getView(Context context,ViewGroup parent);


}
