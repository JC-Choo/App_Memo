package com.example.cnwlc.memo.Util;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by Bridge on 2018-05-16.
 */

public class ToastUtil {
    public static void shortToast(Context context, String message) {
        if(context == null || StringUtil.isEmpty(message))
            return;

        try {
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
        } catch(Exception e) {
            // ignore
        }
    }

    public static void longToast(Context context, String message) {
        if(context == null || StringUtil.isEmpty(message))
            return;

        try {
            Toast.makeText(context, message, Toast.LENGTH_LONG).show();
        } catch(Exception e) {
            // ignore
        }
    }
}
