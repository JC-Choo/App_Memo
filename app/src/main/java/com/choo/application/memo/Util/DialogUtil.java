package com.choo.application.memo.Util;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.choo.application.memo.R;

/**
<<<<<<< HEAD
 * Created by Bridge on 2018-05-06.
=======
 * Created by Bridge on 2018-06-12.
>>>>>>> android
 */

public class DialogUtil extends AlertDialog {

    private String title;
    private String content;
    private View.OnClickListener yesClickListener;
    private View.OnClickListener noClickListener;


    public static DialogUtil getDialog(@NonNull Context context, String title, String content, View.OnClickListener yesListener, View.OnClickListener noListener) {
        DialogUtil dialogUtil = new DialogUtil(context, title, content, yesListener, noListener);
        return dialogUtil;
    }

    public static DialogUtil getDialog(@NonNull Context context, String title, String content, View.OnClickListener yesListener) {
        DialogUtil dialogUtil = new DialogUtil(context, title, content, yesListener);
        return dialogUtil;
    }

    public DialogUtil(@NonNull Context context, String title, String content,
                      View.OnClickListener yesClickListener,
                      View.OnClickListener noClickListener) {
        super(context);
        this.title = title;
        this.content = content;
        this.yesClickListener = yesClickListener;
        this.noClickListener = noClickListener;
    }

    public DialogUtil(Context context, String title, String content,
                      View.OnClickListener yesListener) {
        super(context);
        this.title = title;
        this.content = content;
        this.yesClickListener = yesListener;
    }

    private RelativeLayout relativeLayout;
    private TextView tvTitle;
    private TextView tvContent;
    private TextView tvYes;
    private TextView tvNo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.util_dialog);

        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        layoutParams.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        layoutParams.dimAmount = 0.6f;
        getWindow().setAttributes(layoutParams);
        getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

        relativeLayout = findViewById(R.id.dialog_rl);
        tvTitle = findViewById(R.id.dialog_tv_title);
        tvContent = findViewById(R.id.dialog_tv_content);
        tvYes = findViewById(R.id.dialog_tv_yes);
        tvNo = findViewById(R.id.dialog_tv_no);

        tvTitle.setText(title);
        tvContent.setText(content);
        if (yesClickListener != null && noClickListener != null) {
            tvYes.setOnClickListener(yesClickListener);
            tvNo.setOnClickListener(noClickListener);
        } else if (yesClickListener != null && noClickListener == null) {
            tvYes.setOnClickListener(yesClickListener);
            tvNo.setVisibility(View.GONE);
        }

        relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                return;
            }
        });
    }
}