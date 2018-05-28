package com.example.cnwlc.memo.App.main_memo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.cnwlc.memo.App.main.mvp.MainActivity;
import com.example.cnwlc.memo.Common.Defines;
import com.example.cnwlc.memo.Common.Dlog;
import com.example.cnwlc.memo.R;
import com.example.cnwlc.memo.Util.DateUtil;
import com.example.cnwlc.memo.Util.sqlite.SQLiteUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Bridge on 2018-05-28.
 */

public class FragmentRead extends Fragment {
    @BindView(R.id.readF_text_view_time)
    TextView textViewTime;
    @BindView(R.id.readF_edit_text_content)
    TextView textViewContent;

    public static FragmentRead newInstance() {
        FragmentRead fragment = new FragmentRead();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_memo_read, container, false);
        ButterKnife.bind(this, rootView);

//        int position = getArguments().getInt(Defines.POSITION);
//        Dlog.i("Position______"+position);

//        SQLiteUtil.getInstance().setInintView(getActivity(), Defines.TABLE_MEMO);
//        SQLiteUtil.getInstance().selectMemoRead(position);

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
