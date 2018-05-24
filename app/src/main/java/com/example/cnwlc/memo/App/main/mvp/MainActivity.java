package com.example.cnwlc.memo.App.main.mvp;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cnwlc.memo.App.main.MainItem;
import com.example.cnwlc.memo.App.main.MainRecyclerAdapter;
import com.example.cnwlc.memo.App.main_memo.MemoActivity;
import com.example.cnwlc.memo.Common.BaseActivity;
import com.example.cnwlc.memo.Common.Defines;
import com.example.cnwlc.memo.Common.Dlog;
import com.example.cnwlc.memo.R;
import com.example.cnwlc.memo.Util.PermissionUtil;
import com.example.cnwlc.memo.Util.SharedPreferenceUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Bridge on 2018-05-24.
 */

public class MainActivity extends BaseActivity implements MainContract.View {

    @BindView(R.id.mainA_recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.mainA_text_view_number_of_memos)
    TextView textViewNumberOfMemos;

    private Activity context;
    private InputMethodManager inputMethodManager;

    private MainPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = MainActivity.this;

        presenter = new MainPresenter(this, context);
        presenter.start();

        initView();
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_main;
    }

    @SuppressLint("SetTextI18n")
    private void initView() {
        inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        List<MainItem> mainItemList = new ArrayList<>();
        MainRecyclerAdapter mainRecyclerAdapter = new MainRecyclerAdapter(presenter.setData(mainItemList));
        recyclerView.setAdapter(mainRecyclerAdapter);

        textViewNumberOfMemos.setText(mainRecyclerAdapter.getItemCount()+getString(R.string.count_of_memo));
    }

    @OnClick({R.id.mainA_relative_layout_back, R.id.mainA_image_view_add_memo,
            R.id.mainA_text_view_edit, R.id.mainA_text_view_search})
    public void onClickedSecond(View v) {
        Intent intent = null;

        switch (v.getId()) {
            case R.id.mainA_relative_layout_back :
                Toast.makeText(getApplicationContext(), "wait...", Toast.LENGTH_SHORT).show();
                break;
            case R.id.mainA_image_view_add_memo :
                intent = new Intent(MainActivity.this, MemoActivity.class);
                break;
            case R.id.mainA_text_view_edit :
                Toast.makeText(getApplicationContext(), "wait...", Toast.LENGTH_SHORT).show();
                break;
            case R.id.mainA_text_view_search :
                inputMethodManager.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);
                break;
        }

        if(intent != null)
            startActivity(intent);
    }

    @Override
    public void setPresenter(MainContract.Presenter presenter) { }

    @Override
    public void showPermissionDialog() {
//        PermissionUtil.getInstance().setInitValue(context, recyclerView);
//        PermissionUtil.getInstance().showPermission();
    }
}