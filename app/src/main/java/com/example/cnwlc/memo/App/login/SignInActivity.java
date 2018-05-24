package com.example.cnwlc.memo.App.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

import com.example.cnwlc.memo.App.main.mvp.MainActivity;
import com.example.cnwlc.memo.Common.BaseActivity;
import com.example.cnwlc.memo.Common.Defines;
import com.example.cnwlc.memo.Common.Dlog;
import com.example.cnwlc.memo.R;
import com.example.cnwlc.memo.Util.SharedPreferenceUtil;
import com.example.cnwlc.memo.Util.ToastUtil;
import com.example.cnwlc.memo.Util.sqlite.SQLiteUtil;
//import com.example.cnwlc.memo.Util.sqlite.signup.SQLiteUtilUser;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Bridge on 2018-05-24.
 */

public class SignInActivity extends BaseActivity {
    @BindView(R.id.SignInA_edit_text_id)
    EditText editTextId;
    @BindView(R.id.SignInA_edit_text_password)
    EditText editTextPassword;
    @BindView(R.id.SignInA_check_box_auto_login)
    CheckBox checkBoxAutoLogin;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_sign_in;
    }

    @OnClick({R.id.SignInA_button_login, R.id.SignInA_button_join_membership, R.id.SignInA_button_temporary_login})
    public void onClickButton(View v) {
        Intent intent = null;
        switch (v.getId()) {
            case R.id.SignInA_button_login:
                SQLiteUtil.getInstance().setInintView(this, Defines.DATABASE_USER);
                int result = SQLiteUtil.getInstance().selectLogin(editTextId.getText().toString(), editTextPassword.getText().toString());
                Dlog.i("result_code = " + result);
                SQLiteUtil.getInstance().selectAll();

                if (result == Defines.CODE_1000) {
                    intent = new Intent(SignInActivity.this, MainActivity.class);
                    SharedPreferenceUtil.getInstance().setLoginCheckBox(checkBoxAutoLogin.isChecked());
                    SharedPreferenceUtil.getInstance().setLoginID(editTextId.getText().toString());
                }
                else ToastUtil.shortToast(this, "아이디 또는 비밀번호 정보가 맞지 않습니다.");
                break;
            case R.id.SignInA_button_join_membership:
                intent = new Intent(SignInActivity.this, SignUpActivity.class);
                break;
            case R.id.SignInA_button_temporary_login:
                intent = new Intent(SignInActivity.this, MainActivity.class);
                break;
        }
        if (intent != null)
            startActivity(intent);
    }
}
