package com.example.cnwlc.memo.Test;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.example.cnwlc.memo.Common.BaseActivity;
import com.example.cnwlc.memo.R;
import com.example.cnwlc.memo.Util.sqlite.base.SQLiteUtil;


/**
 * Created by Bridge on 2018-05-17.
 */

public class TestActivity extends BaseActivity {

    private Button btnCreateDatabase, btnInsertDatabase, btnSelectAllData, btnUpdateDatabase, btnDeleteDatabase;
    private ListView lvPeople;

    private String dataBaseName;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        onClickButton();
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_test;
    }

    private void onClickButton() {
        btnCreateDatabase = (Button) findViewById(R.id.btnCreateButton);
        btnCreateDatabase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final EditText etDBName = new EditText(TestActivity.this);
                etDBName.setHint("DB명을 입력하세요.");

                // Dialog로 Database의 이름을 입력받는다.
                AlertDialog.Builder dialog = new AlertDialog.Builder(TestActivity.this);
                dialog.setTitle("Database 이름을 입력하세요.")
                        .setMessage("Database 이름을 입력하세요.")
                        .setView(etDBName)
                        .setPositiveButton("생성", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if (etDBName.getText().toString().length() > 0) {
                                    dataBaseName = etDBName.getText().toString();

                                    SQLiteUtil.getInstance().setInitValue(TestActivity.this, dataBaseName);
                                    SQLiteUtil.getInstance().dataBaseName();
                                }
                            }
                        }).setNeutralButton("취소", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }).create().show();
            }
        });

        btnInsertDatabase = (Button) findViewById(R.id.btnInsertButton);
        btnInsertDatabase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LinearLayout layout = new LinearLayout(TestActivity.this);
                layout.setOrientation(LinearLayout.VERTICAL);

                final EditText etName = new EditText(TestActivity.this);
                etName.setHint(R.string.SQLiteUtil_enter_name);

                final EditText etAge = new EditText(TestActivity.this);
                etAge.setHint(R.string.SQLiteUtil_enter_age);

                final EditText etPhone = new EditText(TestActivity.this);
                etPhone.setHint(R.string.SQLiteUtil_enter_cellphone);

                layout.addView(etName);
                layout.addView(etAge);
                layout.addView(etPhone);

                AlertDialog.Builder dialog = new AlertDialog.Builder(TestActivity.this);
                dialog.setTitle("정보를 입력하세요")
                        .setView(layout)
                        .setPositiveButton("등록", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                SQLiteUtil.getInstance().setData(etName.getText().toString(), etAge.getText().toString(), etPhone.getText().toString());
                            }
                        }).setNeutralButton("취소", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                }).create().show();
            }
        });

        lvPeople = (ListView) findViewById(R.id.lvPeople);
        btnSelectAllData = (Button) findViewById(R.id.btnSelectAllData);
        btnSelectAllData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteUtil.getInstance().showData(lvPeople);
            }
        });

        btnUpdateDatabase = (Button) findViewById(R.id.btnUpdateData);
        btnUpdateDatabase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        btnDeleteDatabase = (Button) findViewById(R.id.btnDeleteData);
        btnDeleteDatabase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
