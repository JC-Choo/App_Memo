package com.choo.application.memo.App.splash;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.choo.application.memo.App.folder.FolderActivity;
import com.choo.application.memo.MemoApplication;
import com.choo.application.memo.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Bridge on 2018-06-12.
 */

public class SplashActivity extends AppCompatActivity {

    @BindView(R.id.splashActivityViewPager)
    ViewPager viewPager;
    @BindView(R.id.splashActivityTabLayout)
    TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);

        MemoApplication.getInstance().getSystemLanguage();
        setViewPager();
    }

    private void setViewPager() {
        SplashViewPagerAdapter splashViewPagerAdapter = new SplashViewPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(splashViewPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
    }

    @OnClick(R.id.splashActivityButton)
    public void onClickStart() {
        Intent intent = new Intent(SplashActivity.this, FolderActivity.class);
        startActivity(intent);
    }
}
