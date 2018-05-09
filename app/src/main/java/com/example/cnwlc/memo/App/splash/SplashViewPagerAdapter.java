package com.example.cnwlc.memo.App.splash;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class SplashViewPagerAdapter extends FragmentStatePagerAdapter {

    public SplashViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return SplashFragment.newInstance(position);
    }

    @Override
    public int getCount() {
        return 4;
    }
}
