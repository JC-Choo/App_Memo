package com.choo.application.memo.App.folder;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.choo.application.memo.App.main.MainActivity;
import com.choo.application.memo.Common.BaseActivity;
import com.choo.application.memo.Common.Dlog;
import com.choo.application.memo.Common.RecyclerItemClickListener;
import com.choo.application.memo.R;
import com.choo.application.memo.Util.DialogEditUtil;
import com.choo.application.memo.Util.DialogUtil;
import com.choo.application.memo.Util.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Bridge on 2018-06-12.
 */

public class FolderActivity extends BaseActivity {

    @BindView(R.id.folderA_recycler_view)
    RecyclerView recyclerView;

    private DialogEditUtil dialogEditUtil;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        init();
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_folder;
    }

    @OnClick({R.id.folderA_text_view_edit, R.id.folderA_text_view_new_folder})
    public void onClickEvent(View v) {
        switch (v.getId()) {
            case R.id.folderA_text_view_edit :
                ToastUtil.shortToast(FolderActivity.this, "wait...");
                break;
            case R.id.folderA_text_view_new_folder :
                dialogEditUtil = new DialogEditUtil(this, getString(R.string.create_new_folder), getString(R.string.dialog_content_create_new_folder), yes, no);
                dialogEditUtil.show();
                break;
        }
    }

    private void init() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        List<FolderItem> folderList = new ArrayList<>();
        FolderItem folderItem = new FolderItem("title", "count");
        folderList.add(folderItem);

        FolderAdapter folderAdapter = new FolderAdapter(folderList);
        recyclerView.setAdapter(folderAdapter);

        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(getApplicationContext(), recyclerView, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(FolderActivity.this, MainActivity.class);
                startActivity(intent);
            }

            @Override
            public void onLongItemClick(View view, int position) {
                Dlog.d("Long_Click_Event");
            }
        }));
    }

    View.OnClickListener yes = new View.OnClickListener() {
        public void onClick(View v) {
            dialogEditUtil.setOnCallBackListener(new DialogEditUtil.CallBackEditTextName() {
                @Override
                public void callBackName(String name) {
                    Dlog.i("name = "+name);
                }
            });

            dialogEditUtil.dismiss();
        }
    };

    View.OnClickListener no = new View.OnClickListener() {
        public void onClick(View v) {
            dialogEditUtil.dismiss();
        }
    };


}
