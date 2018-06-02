package com.choo.application.memo.App.main;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;

import com.choo.application.memo.App.main.fragment_main.FragmentMainViewPagerAdapter;
import com.choo.application.memo.Common.BackPressClose;
import com.choo.application.memo.R;

/**
 * Created by JCChu on 2018-06-02.
 */

public class MainActivity extends FragmentActivity {
    private BackPressClose backPressClose;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        backPressClose = new BackPressClose(this);

        ViewPager viewPager = (ViewPager) findViewById(R.id.mainA_view_pager); // 뷰페이저 세팅
        viewPager.setAdapter(new FragmentMainViewPagerAdapter(getSupportFragmentManager()));    // 뷰페이저의 어댑터를 세팅
    }

    @Override
    public void onBackPressed() {
        backPressClose.onBackPressed();
    }
}