package com.choo.application.memo.App.main_memo;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.choo.application.memo.App.main.mvp.MainActivity;
import com.choo.application.memo.Common.Defines;
import com.choo.application.memo.Common.Dlog;
import com.choo.application.memo.R;
import com.choo.application.memo.Util.ToastUtil;
import com.choo.application.memo.Util.sqlite.SQLiteUtil;

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
    @BindView(R.id.readF_relative_layout_memo)
    RelativeLayout relativeLayoutMemo;

    private String savedTime, savedContent, savedImagePath;
    private int position;

    public static FragmentRead newInstance() {
        return new FragmentRead();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getArguments() != null) {
            position = getArguments().getInt(Defines.POSITION);
            savedTime = getArguments().getString(Defines.MEMO_TIME);
            savedContent = getArguments().getString(Defines.MEMO_CONTENT);
            savedImagePath = getArguments().getString(Defines.MEMO_IMAGE_PATH);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_memo_read, container, false);
        ButterKnife.bind(this, rootView);

        Dlog.i("position = "+position+", savedTime = "+savedTime+", savedContent = "+savedContent+", savedImagePath = "+savedImagePath);

        textViewTime.setText(savedTime);
        textViewContent.setText(savedContent);

        if(position == -1) {
            SQLiteUtil.getInstance().setInintView(getActivity(), Defines.TABLE_MEMO);
            position = SQLiteUtil.getInstance().selectMemoPosition(savedTime, savedContent, savedImagePath);
        }

        Dlog.i("position = "+position);

        relativeLayoutMemo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new FragmentWrite();
                Bundle bundle = new Bundle();
                bundle.putInt(Defines.POSITION, position);
                bundle.putString(Defines.MEMO_TIME, savedTime);
                bundle.putString(Defines.MEMO_CONTENT, savedContent);
                bundle.putString(Defines.MEMO_IMAGE_PATH, savedImagePath);
                fragment.setArguments(bundle);

                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace( R.id.memoA_frame_layout, fragment );
                fragmentTransaction.commit();
            }
        });

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
