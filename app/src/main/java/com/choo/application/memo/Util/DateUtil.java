package com.choo.application.memo.Util;

import android.annotation.SuppressLint;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Bridge on 2018-05-08.
 */

@SuppressLint("SimpleDateFormat")
public class DateUtil {
    public static String getTime(String utcDate) {
        StringBuilder stringBuilder = new StringBuilder();

        int position = utcDate.indexOf(":");
        int start = position - 2;
        int mid = position;
        int end = position + 3;

        String tempHour = utcDate.substring(start, mid);
        int hour = Integer.parseInt(tempHour) + 9;

        if (hour < 12)
            stringBuilder.append(utcDate.substring(0, start)).append(" 오전 ").append(hour).append(utcDate.substring(mid, end));
        else {
            hour -= 12;
            stringBuilder.append(utcDate.substring(0, start)).append(" 오후 ").append(hour).append(utcDate.substring(mid, end));
        }

        return stringBuilder.toString();
    }

    public static String getCurrentTimeYMDAHM() {
        Date date = new Date();

         SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd");
        String tempDate = dateFormat.format(date);

        SimpleDateFormat noonFormat = new SimpleDateFormat("a");
        String tempNoon = noonFormat.format(date);

        SimpleDateFormat dayFormat = new SimpleDateFormat("hh:mm");
        String tempDay = dayFormat.format(date);

        return (tempDate + " " + tempNoon + " " + tempDay);
    }

    public static String getCurrentTimeYMD() {
        Date date = new Date();

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd");
        String tempDate = dateFormat.format(date);

        return tempDate;
    }

    public static String getCurrentTimeAHM() {
        Date date = new Date();

        SimpleDateFormat dayFormat = new SimpleDateFormat("hh:mm");
        String tempDay = dayFormat.format(date);

        SimpleDateFormat noonFormat = new SimpleDateFormat("a");
        String tempNoon = noonFormat.format(date);

        return (tempNoon + " " + tempDay);
    }
}
