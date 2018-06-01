<<<<<<< HEAD:app/src/main/java/com/example/cnwlc/memo/Test/SecondViewPagerAdapter.java
package com.example.cnwlc.memo.Test;
=======
package com.choo.application.memo.App.etc;
>>>>>>> android:app/src/main/java/com/choo/application/memo/Test/SecondViewPagerAdapter.java

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

<<<<<<< HEAD:app/src/main/java/com/example/cnwlc/memo/Test/SecondViewPagerAdapter.java
import com.example.cnwlc.memo.Test.recycler1.RecyclerFragment1;
import com.example.cnwlc.memo.Test.recycler2.RecyclerFragment2;
=======
import com.choo.application.memo.App.etc.recycler1.RecyclerFragment1;
import com.choo.application.memo.App.etc.recycler2.RecyclerFragment2;
>>>>>>> android:app/src/main/java/com/choo/application/memo/Test/SecondViewPagerAdapter.java

public class SecondViewPagerAdapter extends FragmentStatePagerAdapter {
    private int tabItemCount;

    public SecondViewPagerAdapter(FragmentManager fm, int tabItemCount) {
        super(fm);
        this.tabItemCount = tabItemCount;
    }

    @Override
    public Fragment getItem(int position) {
        return getTabFragment(position);
    }

    @Override
    public int getCount() {
        return tabItemCount;
    }

    private Fragment getTabFragment(int position) {
        if(position == 0) {
            return RecyclerFragment1.newInstance();
        } else if(position == 1) {
            return RecyclerFragment2.newInstance();
        } else
            return RecyclerFragment1.newInstance();
    }
}