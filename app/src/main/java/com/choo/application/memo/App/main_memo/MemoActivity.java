package com.choo.application.memo.App.main_memo;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.choo.application.memo.Common.Defines;
import com.choo.application.memo.Common.Dlog;
import com.choo.application.memo.R;
import com.choo.application.memo.Util.sqlite.SQLiteUtil;

import butterknife.ButterKnife;

/**
 * Created by Bridge on 2018-06-14.
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
        Dlog.i("position = "+position);

        SQLiteUtil.getInstance().setInitView(getApplicationContext(), Defines.MEMO);
        String memoAboutPosition = SQLiteUtil.getInstance().selectedMemo(position);

        String[] dividedMemo = new String[0];
        for(int i = 0; i < memoAboutPosition.length(); i++) {
            dividedMemo = memoAboutPosition.split("\\|");
        }

        if(intent.getStringExtra(Defines.MAIN_TO_MEMO).equals(Defines.WRITE)) {
            Fragment fragment = new FragmentWrite();

            Bundle bundle = new Bundle();
            bundle.putInt(Defines.POSITION, position);
            fragment.setArguments(bundle);

            FragmentManager fragmentManager = getFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.add(R.id.memoA_frame_layout, fragment);
            fragmentTransaction.commit();
        } else if(intent.getStringExtra(Defines.MAIN_TO_MEMO).equals(Defines.READ)) {
            Fragment fragment = new FragmentRead();

            Bundle bundle = new Bundle();
            bundle.putInt(Defines.POSITION, position);
            bundle.putString(Defines.MEMO_TIME, dividedMemo[0]);        // key , value
            bundle.putString(Defines.MEMO_CONTENT, dividedMemo[1]);
            bundle.putString(Defines.MEMO_IMAGE_PATH, "");
            fragment.setArguments(bundle);

            FragmentManager fragmentManager = getFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.add(R.id.memoA_frame_layout, fragment);
            fragmentTransaction.commit();
        }
    }
}
