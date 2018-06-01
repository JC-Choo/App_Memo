package com.choo.application.memo.Util;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;

import com.choo.application.memo.R;

/**
 * Created by Bridge on 2018-05-21.
 */

public class ClearEditTextUtil extends RelativeLayout {

    private EditText editText;
    private Button btnClear;

    public ClearEditTextUtil(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setLayout();
    }

    public ClearEditTextUtil(Context context, AttributeSet attrs) {
        super(context, attrs);
        setLayout();
    }

    public ClearEditTextUtil(Context context) {
        super(context);
        setLayout();
    }

    private void setLayout() {
//레이아웃을 설정
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (inflater != null) {
            inflater.inflate(R.layout.util_clear_edit_text, this, true);
        }

        editText = (EditText) findViewById(R.id.clear_edit_text_util_edit_text);
        btnClear = (Button) findViewById(R.id.clear_edit_text_util_button);
        btnClear.setVisibility(RelativeLayout.INVISIBLE);

        clearText();
        showHideClearButton();
    }

    //X버튼이 나타났다 사라지게하는 메소드
    private void showHideClearButton() {
//TextWatcher를 사용해 에디트 텍스트 내용이 변경 될 때 동작할 코드를 입력
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            //에디트 텍스트 안 내용이 변경될 때마다 호출되는 메소드
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() > 0) {
                    btnClear.setVisibility(RelativeLayout.VISIBLE);
                } else {
                    btnClear.setVisibility(RelativeLayout.INVISIBLE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    //에디트 텍스트의 내용을 없애는 메소드
    private void clearText() {
        btnClear.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                editText.setText("");
            }
        });
    }

    //메소드 호출 시 에디트 텍스트 내용을 받아올 수 있는 메소드도 만들어놓는다
    public Editable getText() {
        return editText.getText();
    }
}