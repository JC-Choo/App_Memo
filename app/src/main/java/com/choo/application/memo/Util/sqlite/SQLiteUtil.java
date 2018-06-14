package com.choo.application.memo.Util.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;

import com.choo.application.memo.Common.Defines;
import com.choo.application.memo.Common.Dlog;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Bridge on 2018-06-14.
 */

public class SQLiteUtil {

    private SQLiteUtil() {
    }

    private static class SingleTon {
        public static final SQLiteUtil Instance = new SQLiteUtil();
    }

    public static SQLiteUtil getInstance() {
        return SingleTon.Instance;
    }

    private SQLiteDatabase sqLiteDatabase;
    private String tableName;

    public void setInitView(Context context, String tableName) {
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
            Dlog.e(tableName + " 데이터 베이스를 열수 없음");
        }
    }


    /**
     * SQLite insert
     */
    public void insertFolder(String folderName) {
        ContentValues values = new ContentValues();

        // 키,값의 쌍으로 데이터 입력
        if (tableName.equals(Defines.FOLDER)) {
            values.put(Defines.FOLDER_NAME, folderName);
        }

        long result = sqLiteDatabase.insert(tableName, null, values);
        Dlog.i(tableName + " " + result + "번째 row insert 성공했음");
    }

    public void insertMemo(int folderNameId, String time, String content, String imagePath) {
        ContentValues values = new ContentValues();

        // 키,값의 쌍으로 데이터 입력
        if (tableName.equals(Defines.MEMO)) {
            values.put(Defines.FOLDER_NAME_ID, folderNameId);
            values.put(Defines.TIME, time);
            values.put(Defines.CONTENT, content);
            values.put(Defines.IMAGE_PATH, imagePath);
        }

        long result = sqLiteDatabase.insert(tableName, null, values);
        Dlog.i(tableName + " " + result + "번째 row insert 성공했음");
    }


    /**
     * SQLite update
     */
    // folder name 변경 시 Table FOLDER 변경
    public void updateFolder(int position, String folderName) {
        // 해당 id 가져와서 id 에 맞는 값을 변경하게끔 수정
        ContentValues values = new ContentValues();
        values.put(Defines.FOLDER_NAME, folderName);

        int result = sqLiteDatabase.update(tableName,
                values,    // 뭐라고 변경할지 ContentValues 설정
                "id=?", // 바꿀 항목을 찾을 조건절
                new String[]{String.valueOf((position + 1))});// 바꿀 항목으로 찾을 값 String 배열

        Dlog.i(tableName + " " + result + "번째 row update 성공했음");
    }

    // content, image 변경 시 Table MEMO 변경
    public void updateMemo(int position, int folderNameId, String time, String content, String imagePath) {
        // 해당 id 가져와서 id 에 맞는 값을 변경하게끔 수정
        ContentValues values = new ContentValues();
        values.put(Defines.FOLDER_NAME_ID, folderNameId);
        values.put(Defines.TIME, time);
        values.put(Defines.CONTENT, content);
        values.put(Defines.IMAGE_PATH, imagePath);

        int result = sqLiteDatabase.update(tableName,
                values,    // 뭐라고 변경할지 ContentValues 설정
                "id=?", // 바꿀 항목을 찾을 조건절
                new String[]{String.valueOf((position + 1))});// 바꿀 항목으로 찾을 값 String 배열

        Dlog.i(tableName + " " + result + "번째 row update 성공했음");
    }


    /**
     * SQLite delete
     */
    public void delete(int position) {
        int result = sqLiteDatabase.delete(tableName, "id=?", new String[]{String.valueOf(position + 1)});

        Dlog.i(tableName + " " + result + "개 row delete 성공");
    }


    /**
     * SQLite select
     */
    // FolderActivity 에 가져올 모든 folder name
    public List<String> selectedAllFolder() {
        Cursor c = sqLiteDatabase.query(tableName, null, null, null, null, null, null);
        List<String> stringList = new ArrayList<>();

        while (c.moveToNext()) {
            int id = c.getInt(0);
            String folderName = c.getString(1);

            Dlog.i(tableName + " selectAllFolderName id : " + id + ", folderName : " + folderName);
            stringList.add(folderName);
        }

        return stringList;
    }

    // FragmentMain 에서 Shared 에 저장한 folder_name 에 맞는 모든 memo
    public List<String> selectedAllMemo(int selectedFolderNameId) {
        Cursor c = sqLiteDatabase.query(tableName, null, null, null, null, null, null);
        List<String> userMemo = new ArrayList<>();

        while (c.moveToNext()) {
            int id = c.getInt(0);
            int folderNameId = c.getInt(1);
            String time = c.getString(2);
            String content = c.getString(3);
            String imagePath = c.getString(4);

            if (folderNameId == selectedFolderNameId) {
                Dlog.i(tableName + " selectMemoAll id : " + id + ", selectedFolderNameId : " + selectedFolderNameId + ", time : " + time + ", content : " + content + ", imagePath : " + imagePath);
                userMemo.add(time + "|" + content + "|" + imagePath);
            }
        }

        return userMemo;
    }

    // MemoA -> FragmentRead 로 전달할 해당 position+1 에 맞는 id 값을 확인해 "시간, 내용, 이미지 경로" 를 가져오는 것.
    public String selectedMemo(int position) {
        Cursor c = sqLiteDatabase.query(tableName, null, null, null, null, null, null);

        while (c.moveToNext()) {
            int id = c.getInt(0);
            int folderNameId = c.getInt(1);
            String time = c.getString(2);
            String content = c.getString(3);
            String imagePath = c.getString(4);

            if (id == (position + 1)) {
                Dlog.i(tableName + " selectMemoRead id : " + id + ", folderNameId : " + folderNameId + ", time : " + time + ", content : " + content + ", imagePath : " + imagePath);
                return time + "|" + content + "|" + imagePath;
            }
        }

        return String.valueOf(Defines.CODE_401);
    }





    // FragmentWrite -> FragmentRead 로 가서 position(id)값을 가져오기 위한 method
    public int selectMemoPosition(String memoTime, String memoContent, String memoImagePath) {
        Cursor c = sqLiteDatabase.query(tableName, null, null, null, null, null, null);

        while (c.moveToNext()) {
            int id = c.getInt(0);
            String folderName = c.getString(1);
            String time = c.getString(2);
            String content = c.getString(3);
            String imagePath = c.getString(4);

            if (memoTime.equals(time) && memoContent.equals(content)) {
                Dlog.i(tableName + " selectMemoPosition id : " + id + ", folderName : " + folderName + ", time : " + time + ", content : " + content + ", imagePath : " + imagePath);
                return id;
            }
        }

        return Defines.CODE_401;
    }
}
