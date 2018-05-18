package com.example.cnwlc.memo.App.main_write.mvp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.cnwlc.memo.App.main.mvp.MainActivity;
import com.example.cnwlc.memo.R;
import com.example.cnwlc.memo.Util.DateUtil;
import com.example.cnwlc.memo.Util.ToastUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Bridge on 2018-05-17.
 */

public class WriteActivity extends AppCompatActivity {
    @BindView(R.id.writeA_text_view_time)
    TextView textViewTime;
    @BindView(R.id.writeA_edit_text_content)
    EditText editTextContent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write);
        ButterKnife.bind(this);

        initView();
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
//                Intent intent = new Intent(WriteActivity.this, MainActivity.class);
//                intent.putExtra("write_content", editTextContent.getText().toString());
                ToastUtil.shortToast(WriteActivity.this, "wait...");
                break;
        }
    }
}
