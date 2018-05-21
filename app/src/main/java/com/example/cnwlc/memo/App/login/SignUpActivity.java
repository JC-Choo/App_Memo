package com.example.cnwlc.memo.App.login;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import com.example.cnwlc.memo.R;
import com.example.cnwlc.memo.Util.ToastUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Bridge on 2018-05-21.
 */

public class SignUpActivity extends AppCompatActivity {

    @BindView(R.id.SignUpA_edit_text_id)
    EditText editTextId;
    @BindView(R.id.SignUpA_edit_text_password)
    EditText editTextPassword;
    @BindView(R.id.SignUpA_edit_text_confirm_password)
    EditText editTextPasswordConfirm;
    @BindView(R.id.SignUpA_edit_text_cp)
    EditText editTextMobilePhone;
    @BindView(R.id.SignUpA_edit_text_certification_number)
    EditText editTextCertificationNumber;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        ButterKnife.bind(this);

        setInitView();
    }

    private void setInitView() {

    }

    @OnClick({R.id.SignUpA_relative_layout_back, R.id.SignUpA_button_id_check, R.id.SignUpA_button_certification_number,
            R.id.SignUpA_button_authentication, R.id.SignUpA_button_sign_up, R.id.SignUpA_button_cancellation})
    public void onClickedButton(View v) {
        switch (v.getId()) {
            case R.id.SignUpA_relative_layout_back :
                finish();
                break;
            case R.id.SignUpA_button_id_check :

                break;
            case R.id.SignUpA_button_certification_number :
                if (editTextMobilePhone.getText().toString().equals("")) {
                    ToastUtil.shortToast(this, getString(R.string.SignUpActivity_please_enter_your_phone_number));
                    return;
                } else {
                    int certificationNumber = (int) (Math.random() * 10000);
                    ToastUtil.longToast(this, getString(R.string.SignUpActivity_authentication_number)+" : "+certificationNumber);
                }
                break;
            case R.id.SignUpA_button_authentication :

                break;
            case R.id.SignUpA_button_sign_up :

                break;
            case R.id.SignUpA_button_cancellation :
                finish();
                break;
        }
    }


    @Override
    protected void onStop() {
        super.onStop();
    }
}
