package com.example.cnwlc.memo.App.login;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.cnwlc.memo.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class SignUpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        ButterKnife.bind(this);

    }

    @OnClick({R.id.SignUpA_relative_layout_back})
    public void onClickedButton(View v) {
        switch (v.getId()) {
            case R.id.SignUpA_relative_layout_back :
                finish();
                break;
        }
    }
}
