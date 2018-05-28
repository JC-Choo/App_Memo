package com.example.cnwlc.memo.App.main_memo;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.example.cnwlc.memo.Common.Defines;
import com.example.cnwlc.memo.Common.Dlog;
import com.example.cnwlc.memo.R;

import butterknife.ButterKnife;

/**
 * Created by Bridge on 2018-05-28.
 */

public class MemoActivity extends AppCompatActivity {

    private int position;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memo);
        ButterKnife.bind(this);

        setInitView();
    }

    private void setInitView() {
        Intent intent = getIntent();
        position = intent.getIntExtra(Defines.THE_NUMBER_OF_MEMO, -1);
        if(intent.getStringExtra(Defines.MAIN_TO_MEMO).equals(Defines.WRITE)) {
            Fragment fragment = new FragmentWrite();
            FragmentManager fragmentManager = getFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.add(R.id.memoA_frame_layout, fragment);
            fragmentTransaction.commit();
        } else if(intent.getStringExtra(Defines.MAIN_TO_MEMO).equals(Defines.READ)) {
            Fragment fragment = new FragmentRead();

            Bundle bundle = new Bundle(1); // 파라미터는 전달할 데이터 개수
            bundle.putInt(Defines.POSITION, position); // key , value
            fragment.setArguments(bundle);

            FragmentManager fragmentManager = getFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.add(R.id.memoA_frame_layout, fragment);
            fragmentTransaction.commit();
        }
    }
}
