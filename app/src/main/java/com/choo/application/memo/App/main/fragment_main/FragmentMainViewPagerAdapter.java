package com.choo.application.memo.App.main.fragment_main;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.choo.application.memo.App.main.FragmentMainEtc;
import com.choo.application.memo.App.main.fragment_main.FragmentMain;

/**
 * Created by JCChu on 2018-06-02.
 */

public class FragmentMainViewPagerAdapter extends FragmentPagerAdapter {

    private final int PAGE_COUNT = 2;
//    private String tabTitle[] = new String[]{"Tab1", "Tab2"};
    private Context context;

    public FragmentMainViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    //뷰페이저 내부 뷰를 지정(아이템 지정)
    @Override
    public Fragment getItem(int position) {
        //switch-case문을 사용하여 페이지 포지션 별로 그에 맞는 Fragment를 리턴하여 화면을 작성한다
        switch (position) {
            case 0:
                return new FragmentMain();
            case 1:
                return new FragmentMainEtc();
        }

        //페이지 포지션이 없는 곳으로 이동을 하려는 경우 null을 리턴하여 움직임이 없게 한다
        return null;
    }

    //뷰페이저 페이지 수를 지정
    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

    // 페이지 타이틀을 지정, 포지션을 이용하여 페이지별 이름을 다 다르게도 설정가능
//    @Override
//    public CharSequence getPageTitle(int position) {
//        return tabTitle[position];
//    }
}
