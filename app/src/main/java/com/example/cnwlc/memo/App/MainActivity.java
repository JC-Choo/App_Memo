package com.example.cnwlc.memo.App;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.example.cnwlc.memo.App.etc.SecondActivity;
import com.example.cnwlc.memo.Common.BaseActivity;
import com.example.cnwlc.memo.R;
import com.example.cnwlc.memo.Util.PermissionUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {

    @BindView(R.id.mainA_recycler_view)
    RecyclerView recyclerView;

    private Activity context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = MainActivity.this;

        initView();
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_main;
    }

    private void initView() {
//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
//        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
//        recyclerView.setHasFixedSize(true);
//        recyclerView.setLayoutManager(linearLayoutManager);

        PermissionUtil.getInstance().PermissionUtil(context, recyclerView);
        PermissionUtil.getInstance().showPermission();

        List<Item> items = new ArrayList<Item>();
        for (int i = 0; i < 20; i++) {
            Item item = new Item("어느", "멋진", "날");
            items.add(item);
        }

        recyclerView.setAdapter(new RecyclerAdapter());
    }

    @OnClick(R.id.mainA_button)
    public void onClickedSecond() {
        Intent intent = new Intent(MainActivity.this, SecondActivity.class);
        startActivity(intent);
    }
}