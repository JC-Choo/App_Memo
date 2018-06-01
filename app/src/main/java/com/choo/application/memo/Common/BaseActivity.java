package com.choo.application.memo.Common;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.readystatesoftware.systembartint.SystemBarTintManager;

import butterknife.ButterKnife;

/**
 * Created by Bridge on 2018-05-06.
 */


public abstract class BaseActivity extends AppCompatActivity {
    private BackPressClose backPressClose;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentViewId());
        ButterKnife.bind(this);

        backPressClose = new BackPressClose(this);

        setVersionCompatibility();
        checkNetworkStatus();
    }

    private void setVersionCompatibility() {
        View view = getWindow().getDecorView();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (view != null) {
                // 23 버전 이상일 때
//                view.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            }
        } else if (Build.VERSION.SDK_INT >= 21) {
            // 21 버전 이상일 때
            getWindow().setStatusBarColor(Color.WHITE);
        }

        if (Build.VERSION.SDK_INT == Build.VERSION_CODES.KITKAT) {
            SystemBarTintManager tintManager = new SystemBarTintManager(this);
            tintManager.setStatusBarTintEnabled(true);
            tintManager.setStatusBarTintColor(Color.parseColor("#014070"));
//            tintManager.setTintColor(Color.parseColor("#014070"));
        }
    }

    private void checkNetworkStatus() {
    }

    protected abstract int getContentViewId();

    @Override
    public void onBackPressed() {
        backPressClose.onBackPressed();
    }
}
