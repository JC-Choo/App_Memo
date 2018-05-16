package com.example.cnwlc.memo.App.main_write.mvp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.example.cnwlc.memo.Common.BaseActivity;
import com.example.cnwlc.memo.R;
import com.example.cnwlc.memo.Util.DateUtil;
import com.example.cnwlc.memo.Util.ToastUtil;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Bridge on 2018-05-16.
 */

public class WriteActivity extends BaseActivity {
    @BindView(R.id.writeA_text_view_time)
    TextView textViewTime;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initView();
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_write;
    }

    private void initView() {
        textViewTime.setText(DateUtil.getCurrentTimeYMDAHM());
    }

    @OnClick({R.id.writeA_relative_layout_back, R.id.writeA_text_view_completion})
    public void onClickMethod(View v) {
        switch (v.getId()) {
            case R.id.writeA_relative_layout_back :
                finish();
                break;
            case R.id.writeA_text_view_completion :
                ToastUtil.shortToast(this, "wait...");
                break;
        }
    }
}
