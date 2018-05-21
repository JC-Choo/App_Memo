package com.example.cnwlc.memo.Common;

import android.app.Activity;
import android.support.v4.app.ActivityCompat;

import com.example.cnwlc.memo.MemoApplication;
import com.example.cnwlc.memo.R;
import com.example.cnwlc.memo.Util.ToastUtil;

/**
 * Created by Bridge on 2018-05-06.
 */


public class BackPressClose {
    private Activity activity;
    private float pressedTime;

    public BackPressClose(Activity context) {
        this.activity = context;
    }

    public void onBackPressed() {
        if (pressedTime == 0) {
            pressedTime = System.currentTimeMillis();
            ToastUtil.longToast(activity, MemoApplication.getInstance().getString(R.string.back_press));
        } else {
            int seconds = (int) (System.currentTimeMillis() - pressedTime);

            if (seconds > 2000) pressedTime = 0;
            else {
//                activity.finish();
                ActivityCompat.finishAffinity(activity);
                System.runFinalizersOnExit(true);
                System.exit(0);
            }
        }
    }
}
