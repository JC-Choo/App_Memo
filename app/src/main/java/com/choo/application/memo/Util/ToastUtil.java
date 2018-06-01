package com.choo.application.memo.Util;

import android.app.Activity;
import android.content.Context;
import android.widget.Toast;

/**
 * Created by Bridge on 2018-05-17.
 */

public class ToastUtil {
    public static void shortToast(Activity context, String message) {
        if(context == null || StringUtil.isEmpty(message))
            return;

        try {
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
        } catch(Exception e) {
            // ignore
        }
    }

    public static void longToast(Activity context, String message) {
        if(context == null || StringUtil.isEmpty(message))
            return;

        try {
            Toast.makeText(context, message, Toast.LENGTH_LONG).show();
        } catch(Exception e) {
            // ignore
        }
    }
}
