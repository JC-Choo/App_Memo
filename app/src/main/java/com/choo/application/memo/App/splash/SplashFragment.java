package com.choo.application.memo.App.splash;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.choo.application.memo.Common.Defines;
import com.choo.application.memo.R;

/**
 * Created by JCChu on 2018-06-02.
 */

public class SplashFragment extends Fragment {
    private int pageNumber;

    public static SplashFragment newInstance(int pageNumber) {
        SplashFragment splashFragment = new SplashFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(Defines.PAGE_NUMBER, pageNumber);
        splashFragment.setArguments(bundle);

        return splashFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            pageNumber = getArguments().getInt(Defines.PAGE_NUMBER);
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView;

        if(pageNumber == 0) rootView = inflater.inflate(R.layout.fragment_splash_first, container, false);
        else if(pageNumber == 1) rootView = inflater.inflate(R.layout.fragment_splash_second, container, false);
        else if(pageNumber == 2) rootView = inflater.inflate(R.layout.fragment_splash_third, container, false);
        else rootView = inflater.inflate(R.layout.fragment_splash_forth, container, false);

        return rootView;
    }
}
