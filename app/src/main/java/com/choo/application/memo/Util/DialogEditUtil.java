package com.choo.application.memo.Util;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.choo.application.memo.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Bridge on 2018-06-15.
 */

public class DialogEditUtil extends AlertDialog {

    private String title;
    private String content;
    private int position;
    private View.OnClickListener yesClickListener;
    private View.OnClickListener noClickListener;

    private CallBackEditTextName callBackEditTextName;
    public interface CallBackEditTextName {
        void callBackNamePosition(String name, int position);
    }

    public static DialogEditUtil getDialog(@NonNull Context context, String title, String content, int position, View.OnClickListener yesListener, View.OnClickListener noListener) {
        return new DialogEditUtil(context, title, content, position, yesListener, noListener);
    }

    public DialogEditUtil(@NonNull Context context, String title, String content, int position,
                          View.OnClickListener yesClickListener, View.OnClickListener noClickListener) {
        super(context);
        this.title = title;
        this.content = content;
        this.position = position;
        this.yesClickListener = yesClickListener;
        this.noClickListener = noClickListener;
    }

    @BindView(R.id.utilDialogEdit_relative_layout)
    RelativeLayout relativeLayout;
    @BindView(R.id.utilDialogEdit_text_view_title)
    TextView textViewTitle;
    @BindView(R.id.utilDialogEdit_text_view_content)
    TextView textViewContent;
    @BindView(R.id.utilDialogEdit_text_view_yes)
    TextView textViewYes;
    @BindView(R.id.utilDialogEdit_text_view_no)
    TextView textViewNo;
    @BindView(R.id.utilDialogEdit_edit_text_name)
    EditText editTextName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.util_dialog_edit);
        ButterKnife.bind(this);

        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        layoutParams.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        layoutParams.dimAmount = 0.6f;

        getWindow().setAttributes(layoutParams);
        getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

        textViewTitle.setText(title);
        textViewContent.setText(content);

        if (yesClickListener != null && noClickListener != null) {
            textViewYes.setOnClickListener(yesClickListener);
            textViewNo.setOnClickListener(noClickListener);
        }

        relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                return;
            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();

        callBackEditTextName.callBackNamePosition(editTextName.getText().toString(), position);
    }

    public void setOnCallBackListener(CallBackEditTextName listener) {
        callBackEditTextName = listener;
    }
}