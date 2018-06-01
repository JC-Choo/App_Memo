package com.example.cnwlc.memo.Util.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;

import com.example.cnwlc.memo.Common.Defines;
import com.example.cnwlc.memo.Common.Dlog;
import com.example.cnwlc.memo.Util.SharedPreferenceUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Bridge on 2018-06-01.
 */

public class SQLiteUtil {

    private SQLiteUtil() {}
    private static class SingleTon {
        public static final SQLiteUtil Instance = new SQLiteUtil();
    }
    public static SQLiteUtil getInstance() {
        return SingleTon.Instance;
    }

    private SQLiteDatabase sqLiteDatabase;
    private String tableName;
    public void setInintView(Context context, String tableName) {
        this.tableName = tableName;

        DBHelper helper = new DBHelper(
                context,  // 현재 화면의 제어권자
                Defines.DATABASE_NAME,  // 데이터베이스 이름
                null, // 커서팩토리 - null 이면 표준 커서가 사용됨
                3);

        try {
            sqLiteDatabase = helper.getWritableDatabase();
        } catch (SQLiteException e) {
            e.printStackTrace();
            Dlog.e(tableName +" 데이터 베이스를 열수 없음");
        }
    }

    public void insert(String first, String second, String third) {
        ContentValues values = new ContentValues();
        // 키,값의 쌍으로 데이터 입력
        if(tableName.equals(Defines.TABLE_USER)) {
            values.put(Defines.ID, first);
            values.put(Defines.PASSWORD, second);
            values.put(Defines.CELLPHONE, third);
        } else if(tableName.equals(Defines.TABLE_MEMO)) {
            values.put(Defines.ID, SharedPreferenceUtil.getInstance().getLoginID());
            values.put(Defines.TIME, first);
            values.put(Defines.CONTENT, second);
            values.put(Defines.IMAGE_PATH, third);
        }

        long result = sqLiteDatabase.insert(tableName, null, values);
        Dlog.i(tableName+" "+result + "번째 row insert 성공했음");
    }

    public void delete(int number) {
        int result = sqLiteDatabase.delete(tableName, "no=?", new String[]{String.valueOf(number)});

        Dlog.i(tableName +" "+ result + "개 row delete 성공");
    }

    public void update(String first, String second, String third) {
        // 해당 _no 가져와서 _no 에 맞는 값을 변경하게끔 수정
        ContentValues values = new ContentValues();
        values.put(Defines.ID, SharedPreferenceUtil.getInstance().getLoginID());
        values.put(Defines.TIME, first);
        values.put(Defines.CONTENT, second);
        values.put(Defines.IMAGE_PATH, third);

        int result = sqLiteDatabase.update(tableName,
                values,    // 뭐라고 변경할지 ContentValues 설정
                "ID=?", // 바꿀 항목을 찾을 조건절
                new String[]{SharedPreferenceUtil.getInstance().getLoginID()});// 바꿀 항목으로 찾을 값 String 배열

        Dlog.i(tableName+" "+result + "번째 row update 성공했음");
    }

    // 로그인 시에 가져온 id와 pw를 SQLite의 data와 비교하기 위한 method
    public int selectLogin(String loginID, String loginPassword) {
        Cursor c = sqLiteDatabase.query(tableName, null, null, null, null, null, null);

        while (c.moveToNext()) {
            String id = c.getString(1);
            String password = c.getString(2);

            if(id.equals(loginID) && password.equals(loginPassword)) {
                Dlog.i(tableName+" id : " + id + ", password : " + password);
                return Defines.CODE_1000;
            }
        }

        return Defines.CODE_401;
    }

    // MainA에서 클릭을 통해 Shared에 저장한 id와 메모 번호를 SQLite 의 data 와 비교하기 위한 method
    public String selectMemoRead(int position) {
        Cursor c = sqLiteDatabase.query(tableName, null, null, null, null, null, null);

        while (c.moveToNext()) {
            int _no = c.getInt(0);
            String id = c.getString(1);
            String time = c.getString(2);
            String content = c.getString(3);
            String imagePath = c.getString(4);


            if( _no == (position+1) ) {
                Dlog.i(tableName+" selectMemoRead _no : " + _no+", id : " + id + ", time : " + time + ", content : " + content + ", imagePath : " + imagePath);
                return time+"|"+content+"|"+imagePath;
            }
        }

        return String.valueOf(Defines.CODE_401);
    }

    // MainA에서 Shared 에 저장한 id에 맞는 memo 를 보여주기 위해 SQLite 의 data 와 비교하기 위한 method
    public List<String> selectMemoAll() {
        Cursor c = sqLiteDatabase.query(tableName, null, null, null, null, null, null);

        List<String> userMemo = new ArrayList<>();

        while (c.moveToNext()) {
            int _no = c.getInt(0);
            String id = c.getString(1);
            String time = c.getString(2);
            String content = c.getString(3);
            String imagePath = c.getString(4);


            if( id.equals(SharedPreferenceUtil.getInstance().getLoginID()) ) {
                Dlog.i(tableName+" selectMemoAll _no : " + _no+", id : " + id + ", time : " + time + ", content : " + content + ", imagePath : " + imagePath);
                userMemo.add(time + "|" + content + "|" + imagePath);
            }
        }

        return userMemo;
    }

    public void selectAll() {
        Cursor c = sqLiteDatabase.query(tableName, null, null, null, null, null, null);
        if(tableName.equals(Defines.TABLE_USER)) {
            while (c.moveToNext()) {
                int _no = c.getInt(0);
                String id = c.getString(1);
                String password = c.getString(2);
                String cellphone = c.getString(3);

                Dlog.i(tableName+" selectAll _no : " + _no + ", id : " + id + ", password : " + password + ", cellphone : " + cellphone);
            }
        } else if(tableName.equals(Defines.TABLE_MEMO)) {
            while (c.moveToNext()) {
                int _no = c.getInt(0);
                String id = c.getString(1);
                String time = c.getString(2);
                String content = c.getString(3);
                String imagePath = c.getString(4);

                Dlog.i(tableName+" selectAll _no : " + _no + ", id : " + id + ", time : " + time + ", content : " + content + ", imagePath : " + imagePath);
            }
        }
    }
}
