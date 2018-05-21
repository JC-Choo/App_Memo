package com.example.cnwlc.memo.Util.sqlite.signup;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.cnwlc.memo.Common.Dlog;
import com.example.cnwlc.memo.MemoApplication;
import com.example.cnwlc.memo.R;
import com.example.cnwlc.memo.Util.ToastUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Bridge on 2018-05-21.
 */

public class DBHelperUser extends SQLiteOpenHelper {
    private Activity context;
    private String dataBaseName;
    private int dataBaseVersion;

    public DBHelperUser(Activity context, String dataBaseName, SQLiteDatabase.CursorFactory factory, int dataBaseVersion) {
        super(context, dataBaseName, factory, dataBaseVersion);

        this.context = context;
        this.dataBaseName = dataBaseName;
        this.dataBaseVersion = dataBaseVersion;
    }

    // Database 가 존재하지 않을 때, 딱 한번 실행된다. DB를 만드는 역할을 한다.
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        // String 보다 StringBuffer가 Query 만들기 편하다.
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(" CREATE TABLE "+dataBaseName+" ( ");
        stringBuffer.append(" _NO INTEGER PRIMARY KEY AUTOINCREMENT, ");
        stringBuffer.append(" ID TEXT, ");
        stringBuffer.append(" PASSWORD TEXT, ");
        stringBuffer.append(" PHONE TEXT ) ");

        // SQLite Database로 쿼리 실행
        sqLiteDatabase.execSQL(stringBuffer.toString());
        Dlog.i("dataBaseName : "+dataBaseName);
        Dlog.i("stringBuffer : "+stringBuffer.toString());
        ToastUtil.shortToast(context, dataBaseName+" "+ MemoApplication.getInstance().getString(R.string.DBHelper_make_table));
    }

    private void createTableUser(String dataBaseName, SQLiteDatabase sqLiteDatabase) {
    }

    // Application 의 버전이 올라가서 Table 구조가 변경되었을 때 실행된다.
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Dlog.d("db : "+db+", oldVersion : "+oldVersion+", newVersion : "+newVersion);
        Dlog.d(MemoApplication.getInstance().getString(R.string.DBHelper_change_version));
    }

    // Data 를 Insert 하기 위한 method
    public void insertDataUser(User user) {
        // 1. 쓸 수 있는 DB 객체를 가져온다.
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();

        // 2. Person Data를 Insert한다.
        // _no는 자동으로 증가하기 때문에 넣지 않습니다.
        String stringBuffer = " INSERT INTO "+dataBaseName+" ( ID, PASSWORD, PHONE ) VALUES ( ?, ?, ? ) ";

        sqLiteDatabase.execSQL(stringBuffer, new Object[]{
                user.getId(), user.getPassword(), user.getPhone()
        });

        ToastUtil.shortToast(context, dataBaseName+" "+MemoApplication.getInstance().getString(R.string.DBHelper_insert_data));
        sqLiteDatabase.close();
    }

    // Data 를 select 하기 위한 method
    public List selectAllDataUser() {
        String stringSelect = " SELECT _NO, ID, PASSWORD, PHONE FROM "+dataBaseName+" ";

        // 읽기 전용 DB 객체를 만든다.
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(stringSelect, null);
        List<User> userList = new ArrayList<>();
        User user = null;

        // moveToNext 다음에 데이터가 있으면 true 없으면 false
        while (cursor.moveToNext()) {
            user = new User();
            user.set_no(cursor.getInt(0));
            user.setId(cursor.getString(1));
            user.setPassword(cursor.getString(2));
            user.setPhone(cursor.getString(3));

            userList.add(user);
        }

        sqLiteDatabase.close();
        return userList;
    }
}