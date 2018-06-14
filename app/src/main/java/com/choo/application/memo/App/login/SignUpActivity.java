package com.choo.application.memo.App.login;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;

import com.choo.application.memo.Common.Defines;
import com.choo.application.memo.R;
import com.choo.application.memo.Util.ToastUtil;
import com.choo.application.memo.Util.sqlite.SQLiteUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Bridge on 2018-05-28.
 */

public class SignUpActivity extends AppCompatActivity {

    @BindView(R.id.SignUpA_edit_text_id)
    EditText editTextId;
    @BindView(R.id.SignUpA_edit_text_password)
    EditText editTextPassword;
    @BindView(R.id.SignUpA_edit_text_confirm_password)
    EditText editTextPasswordConfirm;
    @BindView(R.id.SignUpA_edit_text_cp)
    EditText editTextCellPhone;
    @BindView(R.id.SignUpA_edit_text_certification_number)
    EditText editTextAuthenticationNumber;

    private int certificationNumber;
    private boolean possibleId = false;
    private boolean possibleNumber = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        ButterKnife.bind(this);

        setInitView();
    }

    private void setInitView() {
        if(Build.MODEL.contains("Nexus") || Build.MODEL.contains("nexus")) {
            editTextId.setInputType(InputType.TYPE_CLASS_TEXT);
        }

//        SQLiteUtil.getInstance().setInintView(this, Defines.TABLE_USER);
    }

    @OnClick({R.id.SignUpA_relative_layout_back, R.id.SignUpA_button_id_check, R.id.SignUpA_button_certification_number,
            R.id.SignUpA_button_authentication, R.id.SignUpA_button_join_membership, R.id.SignUpA_button_cancellation})
    public void onClickedButton(View v) {
        switch (v.getId()) {
            case R.id.SignUpA_relative_layout_back :
                finish();
                break;
            case R.id.SignUpA_button_id_check :
                ToastUtil.shortToast(this, getString(R.string.the_id_is_available_to_use));
                possibleId = true;
                break;
            case R.id.SignUpA_button_certification_number :
                if (editTextCellPhone.getText().toString().equals("")) {
                    ToastUtil.shortToast(this, getString(R.string.SignUpActivity_please_enter_your_phone_number));
                    return;
                } else {
                    certificationNumber = (int) (Math.random() * 10000);
                    ToastUtil.longToast(this, getString(R.string.SignUpActivity_authentication_number)+" : "+certificationNumber);
                }
                break;
            case R.id.SignUpA_button_authentication :
                if (editTextAuthenticationNumber.getText().toString().equals(String.valueOf(certificationNumber))) {
                    ToastUtil.shortToast(this, getString(R.string.authentication_was_successful));
                    possibleNumber = true;
                } else {
                    ToastUtil.shortToast(this, getString(R.string.authentication_failed));
                    possibleNumber = false;
                }
                break;
            case R.id.SignUpA_button_join_membership :
                // 이메일 입력 확인
                if (editTextId.getText().toString().length() == 0) {
                    ToastUtil.shortToast(SignUpActivity.this, getString(R.string.enter_id));
                    editTextId.requestFocus();
                    return;
                }
                // 비밀번호 입력 확인
                if (editTextPassword.getText().toString().length() < 4) {
                    ToastUtil.shortToast(SignUpActivity.this, getString(R.string.enter_password));
                    editTextPassword.requestFocus();
                    return;
                }
                // 비밀번호 확인 입력 확인
                if (editTextPasswordConfirm.getText().toString().length() < 4) {
                    ToastUtil.shortToast(SignUpActivity.this, getString(R.string.enter_password_confirm));
                    editTextPasswordConfirm.requestFocus();
                    return;
                }
                // 비밀번호 일치 확인
                if (!editTextPassword.getText().toString().equals(editTextPasswordConfirm.getText().toString())) {
                    ToastUtil.shortToast(SignUpActivity.this, getString(R.string.password_do_not_match));
                    editTextPassword.setText("");
                    editTextPasswordConfirm.setText("");
                    editTextPassword.requestFocus();
                    return;
                }
                // 전화번호 11자리 이상 확인
                if (editTextCellPhone.getText().toString().length() < 10) {
                    ToastUtil.shortToast(SignUpActivity.this, getString(R.string.enter_phone_number));
                    editTextCellPhone.requestFocus();
                    return;
                }
                // 인증번호 입력을 안했을 시
                if (editTextAuthenticationNumber.getText().toString().length() == 0) {
                    ToastUtil.shortToast(SignUpActivity.this, getString(R.string.enter_authentication_number));
                    editTextAuthenticationNumber.requestFocus();
                    return;
                }
                // 중복 체크 안할 경우
                if (!possibleId) {
                    ToastUtil.shortToast(SignUpActivity.this, getString(R.string.check_duplicates));
                    return;
                }
                // 중복 체크 했을 경우
                if (possibleId && possibleNumber) {
//                    SQLiteUtil.getInstance().insert(editTextId.getText().toString(), editTextPassword.getText().toString(), editTextCellPhone.getText().toString());
                    finish();
                }
                break;
            case R.id.SignUpA_button_cancellation :
                finish();
                break;
        }
    }


    @Override
    protected void onStop() {
        super.onStop();

//        SQLiteUtil.getInstance().selectAll();
    }
}
