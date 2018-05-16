package com.example.cnwlc.memo.Util;

<<<<<<< HEAD
=======
import android.annotation.SuppressLint;

>>>>>>> android
import java.text.SimpleDateFormat;
import java.util.Date;

/**
<<<<<<< HEAD
 * Created by Bridge on 2018-05-08.
 */

=======
 * Created by Bridge on 2018-05-16.
 */

@SuppressLint("SimpleDateFormat")
>>>>>>> android
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
<<<<<<< HEAD
            stringBuilder.append(utcDate.substring(0, start) + " 오전 " + hour + utcDate.substring(mid, end));
        else {
            hour -= 12;
            stringBuilder.append(utcDate.substring(0, start) + " 오후 " + hour + utcDate.substring(mid, end));
=======
            stringBuilder.append(utcDate.substring(0, start)).append(" 오전 ").append(hour).append(utcDate.substring(mid, end));
        else {
            hour -= 12;
            stringBuilder.append(utcDate.substring(0, start)).append(" 오후 ").append(hour).append(utcDate.substring(mid, end));
>>>>>>> android
        }

        return stringBuilder.toString();
    }

<<<<<<< HEAD
    public static String getCurrentTime() {
=======
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
>>>>>>> android
        Date date = new Date();

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd");
        String tempDate = dateFormat.format(date);

<<<<<<< HEAD
=======
        return tempDate;
    }

    public static String getCurrentTimeAHM() {
        Date date = new Date();

>>>>>>> android
        SimpleDateFormat dayFormat = new SimpleDateFormat("hh:mm");
        String tempDay = dayFormat.format(date);

        SimpleDateFormat noonFormat = new SimpleDateFormat("a");
        String tempNoon = noonFormat.format(date);

<<<<<<< HEAD
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(tempDate + " " + tempNoon + " " + tempDay);

        return stringBuilder.toString();
=======
        return (tempNoon + " " + tempDay);
>>>>>>> android
    }
}
