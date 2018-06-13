package com.choo.application.memo.Util.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;

import com.choo.application.memo.Common.Defines;
import com.choo.application.memo.Common.Dlog;
import com.choo.application.memo.Util.SharedPreferenceUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Bridge on 2018-06-13.
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
                4);

        try {
            sqLiteDatabase = helper.getWritableDatabase();
        } catch (SQLiteException e) {
            e.printStackTrace();
            Dlog.e(tableName +" 데이터 베이스를 열수 없음");
        }
    }

    public void insertFolderName(String folderName) {
        ContentValues values = new ContentValues();

        // 키,값의 쌍으로 데이터 입력
        if(tableName.equals(Defines.TABLE_FOLDER_NAME)) {
            values.put(Defines.FOLDER_NAME, folderName);
        }

        long result = sqLiteDatabase.insert(tableName, null, values);
        Dlog.i(tableName+" "+result + "번째 row insert 성공했음");
    }

    public void insertMemo(String folderName, String time, String content, String imagePath) {
        ContentValues values = new ContentValues();

        // 키,값의 쌍으로 데이터 입력
        if(tableName.equals(Defines.TABLE_MEMO)) {
            values.put(Defines.FOLDER_NAME, folderName);
            values.put(Defines.TIME, time);
            values.put(Defines.CONTENT, content);
            values.put(Defines.IMAGE_PATH, imagePath);
        }

        long result = sqLiteDatabase.insert(tableName, null, values);
        Dlog.i(tableName+" "+result + "번째 row insert 성공했음");
    }

    public void updateFolder(int position, String folderName) {
        // 해당 _no 가져와서 _no 에 맞는 값을 변경하게끔 수정
        ContentValues values = new ContentValues();
        values.put(Defines.FOLDER_NAME, folderName);

        int result = sqLiteDatabase.update(tableName,
                values,    // 뭐라고 변경할지 ContentValues 설정
                "_no=?", // 바꿀 항목을 찾을 조건절
                new String[]{String.valueOf((position+1))});// 바꿀 항목으로 찾을 값 String 배열

        Dlog.i(tableName+" "+result + "번째 row update 성공했음");
    }

    public void updateMemo(int position, String folderName, String time, String content, String imagePath) {
        // 해당 _no 가져와서 _no 에 맞는 값을 변경하게끔 수정
        ContentValues values = new ContentValues();
        values.put(Defines.FOLDER_NAME, folderName);
        values.put(Defines.TIME, time);
        values.put(Defines.CONTENT, content);
        values.put(Defines.IMAGE_PATH, imagePath);

        int result = sqLiteDatabase.update(tableName,
                values,    // 뭐라고 변경할지 ContentValues 설정
                "_no=?", // 바꿀 항목을 찾을 조건절
                new String[]{String.valueOf((position+1))});// 바꿀 항목으로 찾을 값 String 배열

        Dlog.i(tableName+" "+result + "번째 row update 성공했음");
    }

    public void delete(int position) {
        int result = sqLiteDatabase.delete(tableName, "no=?", new String[]{String.valueOf(position+1)});

        Dlog.i(tableName +" "+ result + "개 row delete 성공");
    }

    // 앱을 켠 뒤 folder 의 이름들을 가져올 method
    public int selectFolderName() {
        Cursor c = sqLiteDatabase.query(tableName, null, null, null, null, null, null);

        while (c.moveToNext()) {
            String folderName = c.getString(1);

            Dlog.i(tableName+" folderName : " + folderName);
            return Defines.CODE_1000;
        }

        return Defines.CODE_401;
    }

    // MemoA -> FragmentRead 로 전달할 해당 position+1 에 맞는 _no 값을 확인해 "시간, 내용, 이미지 경로" 를 가져오는 것.
    public String selectMemoRead(int position) {
        Cursor c = sqLiteDatabase.query(tableName, null, null, null, null, null, null);

        while (c.moveToNext()) {
            int _no = c.getInt(0);
            String folderName = c.getString(1);
            String time = c.getString(2);
            String content = c.getString(3);
            String imagePath = c.getString(4);

            if( _no == (position+1) ) {
                Dlog.i(tableName+" selectMemoRead _no : " + _no+", folderName : " + folderName + ", time : " + time + ", content : " + content + ", imagePath : " + imagePath);
                return time+"|"+content+"|"+imagePath;
            }
        }

        return String.valueOf(Defines.CODE_401);
    }

    // FragmentWrite -> FragmentRead 로 가서 position(_no)값을 가져오기 위한 method
    public int selectMemoPosition(String memoTime, String memoContent, String memoImagePath) {
        Cursor c = sqLiteDatabase.query(tableName, null, null, null, null, null, null);

        while (c.moveToNext()) {
            int _no = c.getInt(0);
            String folderName = c.getString(1);
            String time = c.getString(2);
            String content = c.getString(3);
            String imagePath = c.getString(4);

            if( memoTime.equals(time) && memoContent.equals(content) ) {
                Dlog.i(tableName+" selectMemoPosition _no : " + _no+", folderName : " + folderName + ", time : " + time + ", content : " + content + ", imagePath : " + imagePath);
                return _no;
            }
        }

        return Defines.CODE_401;
    }

    // MainA에서 Shared 에 저장한 id에 맞는 memo 를 보여주기 위해 SQLite 의 data 와 비교하기 위한 method
    public List<String> selectMemoAll(String selectedFolderName) {
        Cursor c = sqLiteDatabase.query(tableName, null, null, null, null, null, null);

        List<String> userMemo = new ArrayList<>();

        while (c.moveToNext()) {
            int _no = c.getInt(0);
            String folderName = c.getString(1);
            String time = c.getString(2);
            String content = c.getString(3);
            String imagePath = c.getString(4);


            if( folderName.equals(selectedFolderName) ) {
                Dlog.i(tableName+" selectMemoAll _no : " + _no+", folderName : " + folderName + ", time : " + time + ", content : " + content + ", imagePath : " + imagePath);
                userMemo.add(time + "|" + content + "|" + imagePath);
            }
        }

        return userMemo;
    }

    public void selectAllFolderName() {
        Cursor c = sqLiteDatabase.query(tableName, null, null, null, null, null, null);
        if(tableName.equals(Defines.TABLE_FOLDER_NAME)) {
            while (c.moveToNext()) {
                int _no = c.getInt(0);
                String folderName = c.getString(1);

                Dlog.i(tableName+" selectAll _no : " + _no + ", folderName : " + folderName);
            }
        }
    }

    public void selectAllMemo() {
        Cursor c = sqLiteDatabase.query(tableName, null, null, null, null, null, null);
        if(tableName.equals(Defines.TABLE_MEMO)) {
            while (c.moveToNext()) {
                int _no = c.getInt(0);
                String folderName = c.getString(1);
                String time = c.getString(2);
                String content = c.getString(3);
                String imagePath = c.getString(4);

                Dlog.i(tableName+" selectAll _no : " + _no + ", folderName : " + folderName + ", time : " + time + ", content : " + content + ", imagePath : " + imagePath);
            }
        }
    }
}
