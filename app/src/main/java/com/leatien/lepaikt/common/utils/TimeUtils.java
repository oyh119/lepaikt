package com.leatien.lepaikt.common.utils;

import android.text.TextUtils;


public class TimeUtils {
    //毫秒换成00:00:00
    public static String getCountTimeByLong(long finishTime) {
        int totalTime = (int) (finishTime / 1000);//秒
        int hour = 0, minute = 0, second = 0;

        if (3600 <= totalTime) {
            hour = totalTime / 3600;
            totalTime = totalTime - 3600 * hour;
        }
        if (60 <= totalTime) {
            minute = totalTime / 60;
            totalTime = totalTime - 60 * minute;
        }
        if (0 <= totalTime) {
            second = totalTime;
        }
        StringBuilder sb = new StringBuilder();

        if (hour < 10) {
            sb.append("0").append(hour).append(":");
        } else {
            sb.append(hour).append(":");
        }
        if (minute < 10) {
            sb.append("0").append(minute).append(":");
        } else {
            sb.append(minute).append(":");
        }
        if (second < 10) {
            sb.append("0").append(second);
        } else {
            sb.append(second);
        }
        return sb.toString();

    }

    /**
     * HH:mm:ss转换成毫秒值
     */
    public  static long dateToLong(String time) {
        long timeMillisecond = 0;
        if (!TextUtils.isEmpty(time)) {
            String[] t = time.split(":");
            timeMillisecond = Integer.parseInt(t[0]) * 60 * 60 * 1000 + Integer.parseInt(t[1]) * 60 * 1000 + Integer.parseInt(t[2]) * 1000;
        }
        return timeMillisecond;
    }
}
