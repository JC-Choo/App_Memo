package com.example.cnwlc.memo.App.main_memo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.example.cnwlc.memo.R;

import butterknife.ButterKnife;

/**
 * Created by Bridge on 2018-05-24.
 */

public class MemoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memo);
        ButterKnife.bind(this);

        setInitView();
    }

    private void setInitView() {
        Fragment fragment = new FragmentWrite();
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.memoA_frame_layout, fragment);
        fragmentTransaction.commit();
    }
}
