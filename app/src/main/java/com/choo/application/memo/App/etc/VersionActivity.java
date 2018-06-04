package com.choo.application.memo.App.etc;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.choo.application.memo.Common.Defines;
import com.choo.application.memo.R;
import com.choo.application.memo.Util.SharedPreferenceUtil;
import com.choo.application.memo.Util.network.NetworkChangeReceiver;
import com.choo.application.memo.VersionChecker;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Bridge on 2018-06-04.
 */

public class VersionActivity extends AppCompatActivity {

    @BindView(R.id.versionActivity_text_view_current_version_2)
    TextView textViewCurrentVersion;
    @BindView(R.id.versionActivity_text_view_new_version_2)
    TextView textViewNewVersion;
    @BindView(R.id.versionActivity_button_update)
    Button buttonUpdate;

    private String appVersion, marketVersion;

    private NetworkChangeReceiver networkChangeReceiver;
    private IntentFilter intentFilter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_version);
        ButterKnife.bind(this);

        setInitView();
    }

    private void setInitView() {
        intentFilter = new IntentFilter("android.intent.action.MAIN");
        networkChangeReceiver = new NetworkChangeReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {

            }
        };
        this.registerReceiver(networkChangeReceiver, intentFilter);

//        if(SharedPreferenceUtil.getInstance().getMarketVersion().equals("")) {
            ReceiveVersion receiveVersion = new ReceiveVersion();
            receiveVersion.execute();
//        }

        appVersion = VersionChecker.getInstance().getAppVersion(this);
        textViewCurrentVersion.setText(appVersion);
    }

    class ReceiveVersion extends AsyncTask<String, String, String> {
        @Override
        protected String doInBackground(String... strings) {
            marketVersion = VersionChecker.getInstance().getMarketVersion();
            SharedPreferenceUtil.getInstance().setMarketVersion(marketVersion);
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            textViewNewVersion.setText(marketVersion);

            // 버전이 다를 경우 업데이트 하게끔 intent 로 이동, 같을 경우 최신버전입니다로 체크.
            if (marketVersion.compareTo(appVersion) > 0) {
                buttonUpdate.setEnabled(true);
                buttonUpdate.setText(getString(R.string.update_version));
            } else {
                buttonUpdate.setEnabled(false);
                buttonUpdate.setText(getString(R.string.current_new_version));
            }
        }
    }

    @OnClick({R.id.versionActivity_relative_layout_back, R.id.versionActivity_button_update})
    public void onClickEvent(View v) {
        switch (v.getId()) {
            case R.id.versionActivity_relative_layout_back :
                finish();
                break;
            case R.id.versionActivity_button_update :
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(Defines.MARKET_APP_ADDRESS));
                startActivity(intent);
                break;
        }
    }

    @Override
    protected void onPause() {
        super.onPause();

        this.unregisterReceiver(this.networkChangeReceiver);
    }
}
