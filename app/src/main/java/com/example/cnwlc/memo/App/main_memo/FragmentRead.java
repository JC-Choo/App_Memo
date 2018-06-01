package com.example.cnwlc.memo.App.main_memo;

import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.cnwlc.memo.App.main.mvp.MainActivity;
import com.example.cnwlc.memo.Common.Defines;
import com.example.cnwlc.memo.Common.Dlog;
import com.example.cnwlc.memo.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Bridge on 2018-06-01.
 */

public class FragmentRead extends Fragment {
    @BindView(R.id.readF_text_view_time)
    TextView textViewTime;
    @BindView(R.id.readF_edit_text_content)
    TextView textViewContent;

    private String time, content, imagePath;

    public static FragmentRead newInstance(String time, String content, String imagePath) {
        FragmentRead fragment = new FragmentRead();
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getArguments() != null) {
            time = getArguments().getString(Defines.MEMO_TIME);
            content = getArguments().getString(Defines.MEMO_CONTENT);
            imagePath = getArguments().getString(Defines.MEMO_IMAGE_PATH);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_memo_read, container, false);
        ButterKnife.bind(this, rootView);

        Dlog.i("time______"+time);
        Dlog.i("content______"+content);
        Dlog.i("imagePath______"+imagePath);

        textViewTime.setText(time);
        textViewContent.setText(content);

        return rootView;
    }

    @OnClick({R.id.readF_relative_layout_back})
    public void onClickEvent(View v) {
        Intent intent;

        switch (v.getId()) {
            case R.id.readF_relative_layout_back :
                intent = new Intent(getActivity(), MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                break;
        }
    }
}
