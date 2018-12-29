package com.leatien.lepaikt.common.utils;

import android.support.annotation.NonNull;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;

import java.text.DecimalFormat;

/**
 * Created by oyh on 2018/11/29
 */
public class StringUtils {
    static DecimalFormat df = new DecimalFormat("0.00");

    /**
     * 百分比转换
     *
     * @param str 字符
     * @return String
     */
    public static String convert(String str) {
        return df.format(Double.parseDouble(str) * 100) + "";
    }

    public static String convert(int str) {
        return df.format(Double.parseDouble(str + "") * 100) + "";
    }

    public static String convert(double str) {
        return df.format(str * 100) + "";
    }


    /**
     * 保留两位小数
     *
     * @param str str
     * @return String
     */
    public static String reserveTwoDecimals(String str) {
        return df.format(Double.parseDouble(str)) + "";
    }

    public static String reserveTwoDecimals(int str) {
        return df.format(Double.parseDouble(str + "")) + "";
    }

    public static String reserveTwoDecimals(double str) {
        return df.format(str) + "";
    }

    /**
     * 改变TextView指定字符或者字符串大小,包含两端start，但不包含end所在的端点
     *
     * @param setText 你要修改的字符串
     * @param size    你要修改的字体大小 接受的是px
     * @param start   开始位置
     * @param end     结束位置
     * @return Spannable
     */
    public static Spannable changeTextViewCharSize(@NonNull String setText, int size, int start, int end) {
        Spannable spannable = new SpannableString(setText);
        spannable.setSpan(new AbsoluteSizeSpan(size), start, end, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        return spannable;
    }

    /**
     * 修改textView指定位置字体的颜色
     *
     * @param setText
     * @param start
     * @param end
     * @param color   要修改的颜色
     * @return
     */
    public static Spannable changeTextViewColor(@NonNull String setText, int start, int end, int color) {
        Spannable spannable = new SpannableString(setText);
        spannable.setSpan(new ForegroundColorSpan(color), start, end, Spanned.SPAN_EXCLUSIVE_INCLUSIVE);
        return spannable;
    }

    /**
     * 同时设置textview指定位置的字体大小和颜色
     *
     * @param setText
     * @param start
     * @param end
     * @param color
     * @param size
     * @return
     */
    public static Spannable changeTextViewColorAndSize(@NonNull String setText, int start, int end, int color, int size) {
        Spannable spannable = new SpannableString(setText);
        spannable.setSpan(new ForegroundColorSpan(color), start, end, Spanned.SPAN_EXCLUSIVE_INCLUSIVE);
        spannable.setSpan(new AbsoluteSizeSpan(size), start, end, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        return spannable;
    }

    /**
     * 如果入参为null 返回 --
     *
     * @param str
     * @return
     */
    public static String isEmptyToGG(String str) {
        return TextUtils.isEmpty(str) ? "--" : str;
    }

    /**
     * 对str非空判断
     *
     * @param str
     * @return
     */
    public static String isEmpty(String str) {
        return TextUtils.isEmpty(str) ? "" : str;
    }


    /**
     * @param oldStr 2017-08-02
     * @return 2017年08月02日
     */
    public static String convertStr(String oldStr) {
        if (!TextUtils.isEmpty(oldStr) && oldStr.contains("-")) {
            String[] split = oldStr.split("-");
            String year = split[0];
            String month = split[1];
            String day = split[2];
            return String.format("%s年%s月%s日", TextUtils.isEmpty(year)?"":year, TextUtils.isEmpty(month)?"":month, TextUtils.isEmpty(day)?"":day);
        }
        return "";
    }


    public static boolean isAllLetter(String str){
        return str.matches("[a-zA-Z]+");
    }
}
