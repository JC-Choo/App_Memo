package com.example.cnwlc.memo.App.main_memo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.cnwlc.memo.R;
import com.example.cnwlc.memo.Util.DateUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Bridge on 2018-05-21.
 */

public class MemoActivity extends AppCompatActivity {

    @BindView(R.id.writeA_text_view_time)
    TextView textViewTime;
    @BindView(R.id.writeA_text_view_completion)
    TextView textViewCompletion;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memo);
        ButterKnife.bind(this);

        setInitView();
    }

    private void setInitView() {
        textViewTime.setText(DateUtil.getCurrentTimeYMDAHM());
        
        Fragment fragment = new FragmentWrite();
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.memoA_frame_layout, fragment);
        fragmentTransaction.commit();
    }

    @OnClick({R.id.memoA_frame_layout, R.id.writeA_text_view_completion})
    public void ChangeFragment( View v ) {
        Fragment fragment = null;
        switch( v.getId() ) {
            case R.id.memoA_frame_layout: {
                fragment = new FragmentWrite();
                break;
            }
            case R.id.writeA_text_view_completion: {
                textViewCompletion.setVisibility(View.GONE);
                fragment = new FragmentRead();
                break;
            }
        }

        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace( R.id.memoA_frame_layout, fragment );
        fragmentTransaction.commit();
    }
}
