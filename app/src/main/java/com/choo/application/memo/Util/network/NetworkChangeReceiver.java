package com.choo.application.memo.Util.network;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.choo.application.memo.Common.Dlog;

/**
 * Created by Bridge on 2018-06-04.
 */

public class NetworkChangeReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        String status = NetworkCheckUtil.getConnectivityStatusString(context);
        Dlog.i("network status = "+status);

        String action = intent.getAction();
        Dlog.i("network action = "+action);

//        String action = intent.getAction();

//        if (action.equals("android.net.conn.CONNECTIVITY_CHANGE")) {
//            if (NetworkCheckUtil.getConnectivityStatus(context).equals(NetworkCheckUtil.Type.NOT_CONNECTED))
//                RxEventBus.getInstance().post(new Events.NetworkChangeEvent());
//        }
    }
}
