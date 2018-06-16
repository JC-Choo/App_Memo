package com.choo.application.memo.App.folder;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.choo.application.memo.App.main.MainActivity;
import com.choo.application.memo.Common.BaseActivity;
import com.choo.application.memo.Common.Defines;
import com.choo.application.memo.Common.Dlog;
import com.choo.application.memo.Common.RecyclerItemClickListener;
import com.choo.application.memo.R;
import com.choo.application.memo.Util.DialogEditUtil;
import com.choo.application.memo.Util.DialogUtil;
import com.choo.application.memo.Util.SharedPreferenceUtil;
import com.choo.application.memo.Util.ToastUtil;
import com.choo.application.memo.Util.sqlite.SQLiteUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Bridge on 2018-06-16.
 */

public class FolderActivity extends BaseActivity {

    @BindView(R.id.folderA_recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.folderA_text_view_edit)
    TextView textViewEdit;
    @BindView(R.id.folderA_text_view_create_folder)
    TextView textViewCreateFolder;
    @BindView(R.id.folderA_text_view_delete_folder)
    TextView textViewDeleteFolder;

    private DialogEditUtil dialogEditUtil;
    private DialogUtil dialogUtil;

    private FolderAdapter folderAdapter;
    private List<FolderItem> folderItemList;
    private boolean dontTouchRecycler = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SQLiteUtil.getInstance().setInitView(getApplicationContext(), Defines.FOLDER);
        initView();
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_folder;
    }

    @Override
    protected void onResume() {
        super.onResume();
        initView();
    }

    private void initView() {
        folderItemList = getFolderList();
        folderAdapter = new FolderAdapter(this, folderItemList, 0);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(folderAdapter);
//        recyclerViewTouchEvent();
    }

    private void recyclerViewTouchEvent() {
        Dlog.i("dontTouchRecycler = "+dontTouchRecycler);
        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(getApplicationContext(), recyclerView, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                if( !dontTouchRecycler ) {
                    SharedPreferenceUtil.getInstance().setFolderNameId(position + 1);
                    Intent intent = new Intent(FolderActivity.this, MainActivity.class);
                    startActivity(intent);
                }
            }

            @Override
            public void onLongItemClick(View view, int position) {
                if( !dontTouchRecycler ) {
                    dialogEditUtil = DialogEditUtil.getDialog(FolderActivity.this, getString(R.string.rename_folder), getString(R.string.please_enter_new_name), (position + 1), updateYes, no);
                    dialogEditUtil.show();
                }
            }
        }));
    }

    private List<FolderItem> getFolderList() {
        List<FolderItem> list = new ArrayList<>();
        List<String> stringList = SQLiteUtil.getInstance().selectedAllFolder();

        for (int i = 0; i < stringList.size(); i++) {
            FolderItem folderItem = new FolderItem();
            folderItem.setStringTitle(stringList.get(i));
            folderItem.setStringCountOfMemos(String.valueOf(i));

            list.add(folderItem);
        }

        return list;
    }

    // 삭제, 새 폴더 추가
    @OnClick({R.id.folderA_text_view_edit, R.id.folderA_text_view_create_folder, R.id.folderA_text_view_delete_folder})
    public void onClickEvent(View v) {
        switch (v.getId()) {
            case R.id.folderA_text_view_edit:
                dontTouchRecycler = true;
                recyclerViewTouchEvent();

                folderAdapter = new FolderAdapter(this, folderItemList, 1);
                recyclerView.setAdapter(folderAdapter);

                textViewEdit.setEnabled(false);
                textViewEdit.setTextColor(getResources().getColor(R.color.gray));
                textViewCreateFolder.setVisibility(View.GONE);
                textViewDeleteFolder.setVisibility(View.VISIBLE);
                break;
            case R.id.folderA_text_view_create_folder:
                dialogEditUtil = DialogEditUtil.getDialog(this, getString(R.string.create_new_folder), getString(R.string.dialog_content_create_new_folder), 0, insertYes, no);
                dialogEditUtil.show();
                break;
            case R.id.folderA_text_view_delete_folder:
                List list = folderAdapter.getSelectedItem();
                StringBuilder sb = new StringBuilder();
                if (list.size() > 0) {
                    for (int index = 0; index < list.size(); index++) {
                        FolderItem model = (FolderItem) list.get(index);
                        sb.append(model.getStringTitle()).append("\n");
                    }
                }
                Dlog.i("sb.toString() = "+sb.toString());
                dialogUtil = DialogUtil.getDialog(this, getString(R.string.yes), sb.toString(), deleteYes, deleteNo);
                dialogUtil.show();
                break;
        }
    }

    // DialogEditUtil 에 대한 updateYes
    View.OnClickListener updateYes = new View.OnClickListener() {
        public void onClick(View v) {
            dialogEditUtil.setOnCallBackListener(new DialogEditUtil.CallBackEditTextName() {
                @Override
                public void callBackNamePosition(String name, int position) {
                    Dlog.i("name = " + name + ", position = " + position);
                    SQLiteUtil.getInstance().updateFolder(position, name);
                }
            });
            dialogUtil.dismiss();
        }
    };

    // DialogEditUtil 에 대한 insertYes, no
    View.OnClickListener insertYes = new View.OnClickListener() {
        public void onClick(View v) {
            dialogEditUtil.setOnCallBackListener(new DialogEditUtil.CallBackEditTextName() {
                @Override
                public void callBackNamePosition(String name, int position) {
                    Dlog.i("name = " + name + ", position = " + position);
                    SQLiteUtil.getInstance().insertFolder(name);
                }
            });

            onResume();
            dialogEditUtil.dismiss();
        }
    };
    View.OnClickListener no = new View.OnClickListener() {
        public void onClick(View v) {
            dialogEditUtil.dismiss();
        }
    };

    // DialogUtil 에 대한 deleteYes, deleteNo
    View.OnClickListener deleteYes = new View.OnClickListener() {
        public void onClick(View v) {
            changedView(1);
            dialogUtil.dismiss();
        }
    };
    View.OnClickListener deleteNo = new View.OnClickListener() {
        public void onClick(View v) {
            changedView(0);
            dialogUtil.dismiss();
        }
    };

    private void changedView(int change) {
        folderAdapter = new FolderAdapter(this, folderItemList, change);
        recyclerView.setAdapter(folderAdapter);

        textViewEdit.setEnabled(true);
        textViewEdit.setTextColor(getResources().getColor(R.color.orange));
        textViewCreateFolder.setVisibility(View.VISIBLE);
        textViewDeleteFolder.setVisibility(View.GONE);
    }
}
