package com.example.cnwlc.memo.App.main_memo;

import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.cnwlc.memo.R;

import butterknife.ButterKnife;

/**
 * Created by Bridge on 2018-05-21.
 */

public class FragmentRead extends Fragment {

    public static FragmentRead newInstance() {
        FragmentRead fragment = new FragmentRead();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_memo_read, container, false);
        ButterKnife.bind(this, rootView);
        return rootView;
    }
}
