package com.choo.application.memo.Util.network;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.choo.application.memo.Common.Defines;

/**
 * Created by Bridge on 2018-06-04.
 */

public class NetworkCheckUtil {
    public static int TYPE_WIFI = 1;
    public static int TYPE_MOBILE = 2;
    public static int TYPE_NOT_CONNECTED = 0;

    public static int getConnectivityStatus(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = connectivityManager.getActiveNetworkInfo();
        if (activeNetwork != null) {
            if (activeNetwork.getType() == ConnectivityManager.TYPE_WIFI)
                return TYPE_WIFI;

            if (activeNetwork.getType() == ConnectivityManager.TYPE_MOBILE)
                return TYPE_MOBILE;
        }
        return TYPE_NOT_CONNECTED;
    }

    public static String getConnectivityStatusString(Context context) {
        int type = NetworkCheckUtil.getConnectivityStatus(context);
        String status = null;

        if (type == TYPE_MOBILE)
            status = Defines.TYPE_MOBILE;
        else if (type == TYPE_WIFI)
            status = Defines.TYPE_WIFI;
        else if (type == TYPE_NOT_CONNECTED)
            status = Defines.TYPE_NOT_CONNECTED;

        return status;
    }
}
