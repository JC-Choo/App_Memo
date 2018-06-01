<<<<<<< HEAD:app/src/main/java/com/example/cnwlc/memo/Test/SecondActivity.java
package com.example.cnwlc.memo.Test;
=======
package com.choo.application.memo.App.etc;
>>>>>>> android:app/src/main/java/com/choo/application/memo/Test/SecondActivity.java

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.choo.application.memo.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SecondActivity extends AppCompatActivity {
    @BindView(R.id.SecondActivityViewPager)
    ViewPager viewPager;
    @BindView(R.id.SecondActivityTabLayout)
    TabLayout tabLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        ButterKnife.bind(this);

        if (tabLayout.getTabCount() != 0)
            tabLayout.removeAllTabs();
        setTabLayout();
        setViewPager();
    }

    private void setTabLayout() {
        tabLayout.addTab(tabLayout.newTab().setText(R.string.first));
        tabLayout.addTab(tabLayout.newTab().setText(R.string.second));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }
    private void setViewPager() {
        viewPager.setAdapter(new SecondViewPagerAdapter(getSupportFragmentManager(), tabLayout.getTabCount()));
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
    }
}
