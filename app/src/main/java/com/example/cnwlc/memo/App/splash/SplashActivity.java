package com.example.cnwlc.memo.App.splash;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.example.cnwlc.memo.App.MainActivity;
import com.example.cnwlc.memo.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

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

        setViewPager();
    }

    private void setViewPager() {
        SplashViewPagerAdapter splashViewPagerAdapter = new SplashViewPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(splashViewPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
    }

    @OnClick(R.id.splashActivityButton)
    public void onClickStart() {
        Intent intent = new Intent(SplashActivity.this, MainActivity.class);
        startActivity(intent);
    }
}
